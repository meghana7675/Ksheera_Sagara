package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ksheera_sagara.R
import com.example.ksheera_sagara.utils.createMonthlyPdf
import com.example.ksheera_sagara.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MonthlySummaryScreen(viewModel: MainViewModel) {

    // =========================
    // SUMMARY DATA
    // =========================
    val summary by viewModel.getMonthlySummary()
        .collectAsState(initial = Triple(0.0, 0.0, 0.0))

    val context = LocalContext.current

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
            modifier = Modifier
                .fillMaxSize()
        ) {

            // =========================
            // BACKGROUND IMAGE
            // =========================
            Image(
                painter = painterResource(id = R.drawable.bg_dairy),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // =========================
            // DARK OVERLAY
            // =========================
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )

            // =========================
            // MAIN CONTENT
            // =========================
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // =========================
                // TITLE
                // =========================
                Text(
                    text = "📊 Monthly Financial Summary",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // =========================
                // SUMMARY CARD
                // =========================
                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 2.dp
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Total Income",
                            color = Color.Gray
                        )

                        Text(
                            text = "₹${String.format("%.2f", summary.first)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Total Expense",
                            color = Color.Gray
                        )

                        Text(
                            text = "₹${String.format("%.2f", summary.second)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Net Profit",
                            color = Color.Gray
                        )

                        Text(
                            text = "₹${String.format("%.2f", summary.third)}",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color =
                                if (summary.third >= 0)
                                    Color(0xFF1B5E20)
                                else
                                    Color.Red
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // =========================
                // DOWNLOAD BUTTON
                // =========================
                Button(
                    onClick = {

                        createMonthlyPdf(
                            context,
                            summary.first,
                            summary.second,
                            summary.third
                        )

                        // =========================
                        // SHOW SNACKBAR
                        // =========================
                        scope.launch {

                            snackbarHostState.showSnackbar(
                                message = "PDF Downloaded Successfully"
                            )
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(18.dp)

                ) {

                    Text(
                        text = "Download PDF Report",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}