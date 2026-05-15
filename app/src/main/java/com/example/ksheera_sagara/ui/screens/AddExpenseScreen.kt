package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ksheera_sagara.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Fodder") }

    // =========================
    // SNACKBAR
    // =========================
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            CommonLayout(title = "Add Expense") {

                // =========================
                // AMOUNT FIELD
                // =========================
                OutlinedTextField(
                    value = amount,

                    onValueChange = {
                        amount = it.replace("\n", "")
                    },

                    label = {
                        Text("Amount")
                    },

                    singleLine = true,

                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // =========================
                // CATEGORY BUTTONS
                // =========================
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    listOf(
                        "Fodder",
                        "Medical",
                        "Labor"
                    ).forEach {

                        Button(
                            onClick = {
                                category = it
                            }
                        ) {

                            Text(it)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // =========================
                // ADD EXPENSE BUTTON
                // =========================
                Button(

                    onClick = {

                        val amt =
                            amount.trim()
                                .toDoubleOrNull() ?: 0.0

                        viewModel.addExpense(
                            amt,
                            category
                        )

                        // CLEAR FIELD
                        amount = ""

                        // SHOW SNACKBAR
                        scope.launch {

                            snackbarHostState.showSnackbar(
                                message = "Expense Added Successfully"
                            )
                        }
                    },

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Add Expense")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // =========================
                // BACK BUTTON
                // =========================
                OutlinedButton(

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
}