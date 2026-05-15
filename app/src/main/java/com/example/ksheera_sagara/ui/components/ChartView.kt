package com.example.ksheera_sagara.ui.components

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.example.ksheerasagara.data.CowMilk

data class CowMilk(
    val day: String,
    val milkLitres: Float
)

@Composable
fun MilkChart(data: List<CowMilk>) {

    AndroidView(
        factory = { context: Context ->
            BarChart(context).apply {

                val entries = data.mapIndexed { index, cow ->
                    BarEntry(index.toFloat(), cow.milkLitres)
                }

                val dataSet = BarDataSet(entries, "Milk Production")
                val barData = BarData(dataSet)

                this.data = barData
                this.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    600
                )

                invalidate()
            }
        }
    )
}