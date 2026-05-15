package com.example.ksheera_sagara.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cowId: Int,
    val liters: Double,
    val fat: Double,
    val amount: Double,
    val date: Long
)