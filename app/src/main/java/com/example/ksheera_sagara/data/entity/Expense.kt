package com.example.ksheera_sagara.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val amount: Double,
    val category: String,
    val date: Long,
    val cowId: Int? = null
)