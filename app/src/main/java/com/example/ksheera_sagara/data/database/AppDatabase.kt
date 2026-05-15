package com.example.ksheera_sagara.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ksheera_sagara.data.dao.CowDao
import com.example.ksheera_sagara.data.dao.ExpenseDao
import com.example.ksheera_sagara.data.dao.IncomeDao
import com.example.ksheera_sagara.data.entity.Cow
import com.example.ksheera_sagara.data.entity.Expense
import com.example.ksheera_sagara.data.entity.Income

@Database(
    entities = [
        Expense::class,
        Cow::class,
        Income::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun cowDao(): CowDao
    abstract fun incomeDao(): IncomeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ksheera_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}