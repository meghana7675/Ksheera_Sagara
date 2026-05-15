package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ksheera_sagara.viewmodel.MainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
@Composable
fun DashboardScreen(viewModel: MainViewModel) {

    var liters by remember { mutableStateOf("") }
    var fat by remember { mutableStateOf("") }
    var cowId by remember { mutableStateOf("") }

    var expenseAmount by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }

    val income by viewModel.income.collectAsState(initial = 0.0)
    val expense by viewModel.totalExpense.collectAsState(initial = 0.0)
    val profit by viewModel.profit.collectAsState(initial = 0.0)
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // =========================
        // 🐄 INCOME SECTION
        // =========================
        Text("Add Milk Income")

        TextField(
            value = cowId,
            onValueChange = { cowId = it },
            label = { Text("Cow ID (Number)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = liters,
            onValueChange = { liters = it },
            label = { Text("Liters") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = fat,
            onValueChange = { fat = it },
            label = { Text("Fat %") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                val id = cowId.toIntOrNull()
                val l = liters.toDoubleOrNull()
                val f = fat.toDoubleOrNull()

                if (id != null && l != null && f != null) {
                    viewModel.addIncome(l, f, id)
                }

                cowId = ""
                liters = ""
                fat = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Income")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // =========================
        // 💸 EXPENSE SECTION
        // =========================
        Text("Add Expense")

        TextField(
            value = expenseAmount,
            onValueChange = { expenseAmount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = expenseCategory,
            onValueChange = { expenseCategory = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.addExpense(
                    expenseAmount.toDoubleOrNull() ?: 0.0,
                    expenseCategory
                )

                expenseAmount = ""
                expenseCategory = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Expense")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // =========================
        // 📊 SUMMARY
        // =========================
        Text("Total Income: ₹$income")
        Text("Total Expense: ₹$expense")
        Text("Profit: ₹$profit")
    }
}