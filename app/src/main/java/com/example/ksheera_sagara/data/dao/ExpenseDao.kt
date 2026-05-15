package com.example.ksheera_sagara.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ksheera_sagara.data.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT IFNULL(SUM(amount), 0) FROM expense_table")
    fun getTotalExpense(): Flow<Double>

    @Query("SELECT * FROM expense_table WHERE cowId = :cowId")
    fun getExpensesByCow(cowId: Int): Flow<List<Expense>>

    // DELETE ALL OLD EXPENSES
    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()
}