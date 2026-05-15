package com.example.ksheera_sagara.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ksheera_sagara.data.AppPrefs
import com.example.ksheera_sagara.utils.createMonthlyPdf
import com.example.ksheera_sagara.viewmodel.MainViewModel

@Composable
fun ReportsScreen(
    viewModel: MainViewModel
) {

    // =========================
    // DATA
    // =========================
    val income by viewModel.income.collectAsState()
    val expense by viewModel.totalExpense.collectAsState()
    val cows by viewModel.cows.collectAsState(initial = emptyList())
    val expensesList by viewModel.expenses.collectAsState(initial = emptyList())

    val incomeD = income.toDouble()
    val expenseD = expense.toDouble()

    val profit = incomeD - expenseD

    val profitColor =
        if (profit >= 0)
            Color(0xFF2E7D32)
        else
            Color.Red

    // =========================
    // SETTINGS
    // =========================
    val context = LocalContext.current
    val prefs = remember { AppPrefs(context) }
    val milkPrice = prefs.getMilkPrice()

    // =========================
    // CHART ANIMATION
    // =========================
    val animatedIncomeAngle by animateFloatAsState(
        targetValue =
            if (incomeD + expenseD == 0.0)
                0f
            else
                ((incomeD / (incomeD + expenseD)) * 360).toFloat(),

        label = ""
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF1F8E9),
                        Color(0xFFE8F5E9),
                        Color.White
                    )
                )
            )
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        // =========================
        // TITLE
        // =========================
        Text(
            text = "Ksheera-Sagara",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Smart Dairy Reports & Analysis",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // =========================
        // EMPTY STATE
        // =========================
        if (cows.isEmpty() && expensesList.isEmpty() && incomeD == 0.0) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "📊",
                        style = MaterialTheme.typography.displayLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "No Data Available Yet",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text =
                            "Start by adding cows, milk income, and expenses to generate smart dairy analytics reports.",

                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "✓ Add Cow\n✓ Add Income\n✓ Add Expense\n✓ Generate Reports",

                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        // =========================
        // INCOME CARD
        // =========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null,
                        tint = Color(0xFF2E7D32)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Total Income",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "₹${String.format("%.2f", incomeD)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF1B5E20),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Milk Price: ₹$milkPrice / litre",
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // =========================
        // EXPENSE CARD
        // =========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Red
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Total Expense",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "₹${String.format("%.2f", expenseD)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // =========================
        // PROFIT CARD
        // =========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = profitColor
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text =
                            if (profit >= 0)
                                "Net Profit"
                            else
                                "Net Loss",

                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "₹${String.format("%.2f", profit)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = profitColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // =========================
        // CHART
        // =========================
        Text(
            text = "Income vs Expense Analysis",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Canvas(
                    modifier = Modifier.size(240.dp)
                ) {

                    drawArc(
                        color = Color(0xFF4CAF50),
                        startAngle = 0f,
                        sweepAngle = animatedIncomeAngle,
                        useCenter = true,
                        size = Size(size.width, size.height)
                    )

                    drawArc(
                        color = Color.Red,
                        startAngle = animatedIncomeAngle,
                        sweepAngle = 360f - animatedIncomeAngle,
                        useCenter = true,
                        size = Size(size.width, size.height)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
// =========================
// AI INSIGHT CARD
// =========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "AI Smart Insight",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =
                        when {

                            incomeD == 0.0 && expenseD == 0.0 ->
                                "No dairy activity recorded yet. Start adding cows, milk income, and expenses to receive smart analytics."

                            profit > 5000 ->
                                "Excellent performance. Your dairy farm is generating strong profits this month."

                            profit > 0 ->
                                "Your dairy farm is profitable. Continue maintaining cattle health and milk quality."

                            else ->
                                "Expenses are currently higher than income. Try reducing feed costs and improving milk yield."
                        },

                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
            }
        }
        // =========================
        // DOWNLOAD BUTTON
        // =========================
        Button(
            onClick = {

                createMonthlyPdf(
                    context = context,
                    income = incomeD,
                    expense = expenseD,
                    profit = profit
                )

                Toast.makeText(
                    context,
                    "PDF Report Downloaded",
                    Toast.LENGTH_LONG
                ).show()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1B5E20)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Download Monthly Report",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}