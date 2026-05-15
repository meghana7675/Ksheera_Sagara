package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ksheera_sagara.viewmodel.MainViewModel

@Composable
fun SummaryScreen(navController: NavController, viewModel: MainViewModel) {

    val income by viewModel.income.collectAsState()
    val expense by viewModel.totalExpense.collectAsState()
    val profit by viewModel.profit.collectAsState()

    CommonLayout("Summary Dashboard") {

        Text("📊 Income: ₹$income")
        Spacer(modifier = Modifier.height(10.dp))

        Text("📉 Expense: ₹$expense")
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "💰 Profit: ₹$profit",
            color = if (profit >= 0)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Simple visual bars
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .padding(4.dp)
        ) {
            LinearProgressIndicator(
                progress = (income / (income + expense + 1)).toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Income vs Expense Ratio")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}