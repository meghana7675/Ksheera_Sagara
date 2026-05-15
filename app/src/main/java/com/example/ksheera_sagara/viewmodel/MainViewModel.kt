package com.example.ksheera_sagara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksheera_sagara.data.dao.CowDao
import com.example.ksheera_sagara.data.dao.ExpenseDao
import com.example.ksheera_sagara.data.entity.Cow
import com.example.ksheera_sagara.data.entity.Expense
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar

class MainViewModel(
    private val dao: ExpenseDao,
    private val cowDao: CowDao
) : ViewModel() {

    // =========================
    // CLEAR OLD EXPENSES ON APP START
    // =========================

    init {
        clearExpensesOnStart()
    }

    private fun clearExpensesOnStart() {

        viewModelScope.launch {
            dao.deleteAllExpenses()
        }
    }

    // =========================
    // EXPENSES
    // =========================

    val expenses: Flow<List<Expense>> =
        dao.getAllExpenses()

    val totalExpense: StateFlow<Double> =
        dao.getTotalExpense().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    // =========================
    // COWS
    // =========================

    val cows: Flow<List<Cow>> =
        cowDao.getAllCows()

    // =========================
    // MILK ENTRY
    // =========================

    private data class MilkEntry(
        val cowId: Int,
        val liters: Double,
        val fat: Double
    )

    private val _milkEntries =
        MutableStateFlow<List<MilkEntry>>(emptyList())

    // =========================
    // CALCULATE INCOME
    // =========================

    private fun calculateIncome(
        list: List<MilkEntry>
    ): Double {

        return list.sumOf {

            val pricePerLiter =
                40 + (it.fat * 2)

            it.liters * pricePerLiter
        }
    }

    // =========================
    // TOTAL INCOME
    // =========================

    val income: StateFlow<Double> =
        _milkEntries.map {
            calculateIncome(it)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    // =========================
    // PROFIT
    // =========================

    val profit: StateFlow<Double> =
        combine(
            income,
            totalExpense
        ) { inc, exp ->

            inc - exp

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    // =========================
    // ADD INCOME
    // =========================

    fun addIncome(
        liters: Double,
        fat: Double,
        cowId: Int?
    ) {

        val entry = MilkEntry(
            cowId = cowId ?: -1,
            liters = liters,
            fat = fat
        )

        _milkEntries.update {
            it + entry
        }
    }

    // =========================
    // ADD EXPENSE
    // =========================

    fun addExpense(
        amount: Double,
        category: String,
        cowId: Int? = null
    ) {

        viewModelScope.launch {

            // DELETE PREVIOUS EXPENSES
            dao.deleteAllExpenses()

            // INSERT NEW EXPENSE
            dao.insert(
                Expense(
                    amount = amount,
                    category = category,
                    date = System.currentTimeMillis(),
                    cowId = cowId
                )
            )
        }
    }

    // =========================
    // ADD COW
    // =========================

    fun addCow(
        name: String,
        breed: String,
        age: String,
        photoUri: String
    ) {

        viewModelScope.launch {

            cowDao.insertCow(
                Cow(
                    name = name,
                    breed = breed,
                    age = age,
                    photoUri = photoUri
                )
            )
        }
    }

    // =========================
    // COW EXPENSE
    // =========================

    fun getCowExpense(
        cowId: Int
    ): Flow<Double> {

        return expenses.map { list ->

            list.filter {
                it.cowId == cowId
            }.sumOf {
                it.amount
            }
        }
    }

    // =========================
    // COW PROFIT
    // =========================

    fun getCowProfit(
        cowId: Int
    ): Flow<Double> {

        val cowIncomeFlow =
            _milkEntries.map { list ->

                list.filter {
                    it.cowId == cowId
                }.sumOf {

                    val price =
                        40 + (it.fat * 2)

                    it.liters * price
                }
            }

        val cowExpenseFlow =
            getCowExpense(cowId)

        return combine(
            cowIncomeFlow,
            cowExpenseFlow
        ) { inc, exp ->

            inc - exp
        }
    }

    // =========================
    // MONTHLY SUMMARY
    // =========================

    fun getMonthlySummary():
            Flow<Triple<Double, Double, Double>> {

        return combine(
            expenses,
            income
        ) { expenseList, incomeValue ->

            val cal =
                Calendar.getInstance()

            val month =
                cal.get(Calendar.MONTH)

            val year =
                cal.get(Calendar.YEAR)

            val monthlyExpense =
                expenseList.filter {

                    val c =
                        Calendar.getInstance()

                    c.timeInMillis = it.date

                    c.get(Calendar.MONTH) == month &&
                            c.get(Calendar.YEAR) == year

                }.sumOf {
                    it.amount
                }

            val profitValue =
                incomeValue - monthlyExpense

            Triple(
                incomeValue,
                monthlyExpense,
                profitValue
            )
        }
    }

    // =========================
    // CATEGORY EXPENSE
    // =========================

    fun getExpenseByCategory(
        category: String
    ): Flow<Double> {

        return expenses.map { list ->

            list.filter {
                it.category == category
            }.sumOf {
                it.amount
            }
        }
    }
}