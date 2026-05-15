package com.example.ksheerasagara.analysis

object AIInsightEngine {

    fun generate(result: CowAnalysisResult): String {

        return """
            🧠 AI INSIGHTS REPORT
            
            ✔ Best Cow: ${result.topCow.cowId} (${result.topCow.milkLitres} L)
            ✔ Weak Cow: ${result.lossCow.cowId} (${result.lossCow.milkLitres} L)

            📊 Total Milk: ${result.totalMilk} L
            📈 Average Milk: ${result.averageMilk} L

            💡 Suggestions:
            - Improve feed for ${result.lossCow.cowId}
            - Maintain care for ${result.topCow.cowId}
            - Overall status: ${
            if (result.averageMilk > 10) "GOOD 👍"
            else "NEEDS IMPROVEMENT ⚠"
        }
        """.trimIndent()
    }
}