package com.example.ksheera_sagara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.*
import com.example.ksheera_sagara.data.database.AppDatabase
import com.example.ksheera_sagara.data.AppPrefs
import com.example.ksheera_sagara.ui.navigation.NavGraph
import com.example.ksheera_sagara.ui.screens.LockScreen
import com.example.ksheera_sagara.viewmodel.MainViewModel
import com.example.ksheera_sagara.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val expenseDao = database.expenseDao()
        val cowDao = database.cowDao()

        val factory = MainViewModelFactory(expenseDao, cowDao)

        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setContent {

            val context = this
            val prefs = AppPrefs(context)

            val lockEnabled = prefs.isLockEnabled()

            var unlocked by remember { mutableStateOf(!lockEnabled) }

            if (unlocked) {

                NavGraph(viewModel)

            } else {

                LockScreen(
                    onUnlock = {
                        unlocked = true
                    }
                )
            }
        }
    }
}