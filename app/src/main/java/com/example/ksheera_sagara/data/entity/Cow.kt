package com.example.ksheera_sagara.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cows")
data class Cow(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val breed: String,
    val age: String,
    val photoUri: String
)