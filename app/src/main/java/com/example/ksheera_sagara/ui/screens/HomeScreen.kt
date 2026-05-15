package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ksheera_sagara.R

@Composable
fun HomeScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // =========================
        // BACKGROUND IMAGE
        // =========================

        Image(
            painter = painterResource(id = R.drawable.bg_dairy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // =========================
        // MAIN CONTENT
        // =========================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =========================
            // TITLE
            // =========================

            Text(
                text = "Ksheera Sagara",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Smart Dairy Management",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(
                modifier = Modifier.height(35.dp)
            )

            // =========================
            // MAIN CARD
            // =========================

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(10.dp),

                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                border = BorderStroke(
                    2.dp,
                    Color.Black
                )
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.spacedBy(16.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // =========================
                    // ADD COW
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("addCow")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Add Cow",
                            fontSize = 16.sp
                        )
                    }

                    // =========================
                    // ADD INCOME
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("income")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Add Income",
                            fontSize = 16.sp
                        )
                    }

                    // =========================
                    // ADD EXPENSE
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("expense")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Add Expense",
                            fontSize = 16.sp
                        )
                    }

                    // =========================
                    // SUMMARY
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("summary")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Summary",
                            fontSize = 16.sp
                        )
                    }
                    Button(
                        onClick = { navController.navigate("monthly_summary") },
                        modifier = Modifier.fillMaxWidth().height(60.dp)
                    ) {
                        Text("Monthly Summary")
                    }
                    Button(
                        onClick = {
                            navController.navigate("cowProfit")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text("Cow Profit Analysis")
                    }
                    // =========================
                    // REPORTS
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("reports")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Reports",
                            fontSize = 16.sp
                        )
                    }

                    // =========================
                    // SETTINGS
                    // =========================

                    Button(
                        onClick = {
                            navController.navigate("settings")
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Settings",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}