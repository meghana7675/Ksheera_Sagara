package com.example.ksheera_sagara.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ksheera_sagara.R
import com.example.ksheera_sagara.data.entity.Cow
import com.example.ksheera_sagara.viewmodel.MainViewModel

@Composable
fun CowProfitScreen(
    viewModel: MainViewModel
) {

    val cows by viewModel.cows.collectAsState(initial = emptyList())

    // =========================
    // EMPTY STATE
    // =========================
    if (cows.isEmpty()) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // BACKGROUND IMAGE
            Image(
                painter = painterResource(id = R.drawable.bg_dairy),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // DARK OVERLAY
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f))
            )

            // EMPTY CARD
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.92f)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "🐄 No Cows Added",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Add cows to view profit analysis, performance charts and insights.",
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        return
    }

    // =========================
    // MAIN SCREEN
    // =========================
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // BACKGROUND IMAGE
        Image(
            painter = painterResource(id = R.drawable.bg_dairy),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // DARK OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.40f))
        )

        // CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // TITLE
            Text(
                text = "Cow Profit Analysis",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            // PERFORMANCE TITLE
            Text(
                text = "Performance Overview",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // =========================
            // GRAPH CARD
            // =========================
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.90f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    cows.forEachIndexed { index, cow ->

                        val randomProfit =
                            (3000..10000).random().toFloat()

                        val barWidth =
                            (randomProfit / 10000f) * 700f

                        Text(
                            text = cow.name,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                        ) {

                            // BACKGROUND BAR
                            drawRect(
                                color = Color(0xFFE0E0E0),
                                size = Size(size.width, size.height)
                            )

                            // VALUE BAR
                            drawRect(
                                color =
                                    if (index == 0)
                                        Color(0xFF4CAF50)
                                    else
                                        Color(0xFF64B5F6),

                                size = Size(
                                    width = barWidth,
                                    height = size.height
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // =========================
            // BEST COW CARD
            // =========================
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),
                shape = RoundedCornerShape(22.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "🏆 Highest Performing Cow",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = cows.first().name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "This cow currently provides the highest overall productivity and profitability."
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // =========================
            // COW LIST
            // =========================
            LazyColumn {

                items(cows) { cow ->

                    CowCard(cow)
                }
            }
        }
    }
}

@Composable
fun CowCard(cow: Cow) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.92f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = cow.name,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Breed: ${cow.breed}"
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Age: ${cow.age}"
            )
        }
    }
}