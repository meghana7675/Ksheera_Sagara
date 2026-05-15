package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ksheera_sagara.viewmodel.MainViewModel

@Composable
fun IncomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var cowId by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var liters by remember { mutableStateOf("") }
    var fat by remember { mutableStateOf("") }
    var snf by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }

    val income by viewModel.income.collectAsState()

    CommonLayout("Milk Income") {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // ======================
            // COW ID
            // ======================

            OutlinedTextField(
                value = cowId,
                onValueChange = { cowId = it },
                label = { Text("Cow ID") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ======================
            // DATE
            // ======================

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                placeholder = { Text("00/00/0000") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ======================
            // LITERS
            // ======================

            OutlinedTextField(
                value = liters,
                onValueChange = { liters = it },
                label = { Text("Liters") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ======================
            // FAT
            // ======================

            OutlinedTextField(
                value = fat,
                onValueChange = { fat = it },
                label = { Text("Fat %") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ======================
            // SNF
            // ======================

            OutlinedTextField(
                value = snf,
                onValueChange = { snf = it },
                label = { Text("SNF") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ======================
            // RATE
            // ======================

            OutlinedTextField(
                value = rate,
                onValueChange = { rate = it },
                label = { Text("Rate per Liter") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ======================
            // BUTTON
            // ======================

            Button(
                onClick = {

                    val litersValue = liters.toDoubleOrNull() ?: 0.0
                    val fatValue = fat.toDoubleOrNull() ?: 0.0

                    viewModel.addIncome(
                        liters.toDoubleOrNull() ?: 0.0,
                        fat.toDoubleOrNull() ?: 0.0,
                        cowId.toIntOrNull()
                    )

                    // CLEAR FIELDS

                    cowId = ""
                    date = ""
                    liters = ""
                    fat = ""
                    snf = ""
                    rate = ""

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Income")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ======================
            // TOTAL INCOME
            // ======================

            Text(
                text = "Total Income: ₹$income",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ======================
            // BACK BUTTON
            // ======================

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}