package com.example.ksheera_sagara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ksheera_sagara.data.dao.CowDao
import com.example.ksheera_sagara.data.dao.ExpenseDao

class MainViewModelFactory(
    private val expenseDao: ExpenseDao,
    private val cowDao: CowDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")

            return MainViewModel(
                expenseDao,
                cowDao
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}