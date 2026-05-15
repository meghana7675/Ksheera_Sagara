package com.example.ksheera_sagara.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ksheera_sagara.data.entity.Cow
import kotlinx.coroutines.flow.Flow

@Dao
interface CowDao {

    @Insert
    suspend fun insertCow(cow: Cow)

    @Query("SELECT * FROM cows")
    fun getAllCows(): Flow<List<Cow>>
}