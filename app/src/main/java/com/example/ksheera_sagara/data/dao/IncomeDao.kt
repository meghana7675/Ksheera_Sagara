package com.example.ksheera_sagara.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ksheera_sagara.data.entity.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Insert
    suspend fun insert(income: Income)

    @Query("SELECT * FROM income ORDER BY date DESC")
    fun getAllIncome(): Flow<List<Income>>
}