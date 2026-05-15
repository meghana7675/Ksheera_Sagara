package com.example.ksheerasagara.analysis

import com.example.ksheerasagara.data.CowMilk

data class CowAnalysisResult(
    val topCow: CowMilk,
    val lossCow: CowMilk,
    val totalMilk: Float,
    val averageMilk: Float
)

object CowAnalyzer {

    fun analyze(data: List<CowMilk>): CowAnalysisResult {

        val topCow = data.maxByOrNull { it.milkLitres }!!
        val lossCow = data.minByOrNull { it.milkLitres }!!

        val total = data.sumOf { it.milkLitres.toDouble() }.toFloat()
        val avg = total / data.size

        return CowAnalysisResult(
            topCow,
            lossCow,
            total,
            avg
        )
    }
}