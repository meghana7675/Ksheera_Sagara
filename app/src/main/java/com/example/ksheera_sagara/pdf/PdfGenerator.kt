package com.example.ksheerasagara.pdf

import android.content.Context
import android.graphics.*
import android.graphics.pdf.PdfDocument
import com.example.ksheerasagara.analysis.CowAnalysisResult
import com.example.ksheerasagara.data.CowMilk
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun createReport(
        context: Context,
        data: List<CowMilk>,
        result: CowAnalysisResult,
        insight: String
    ): File {

        val pdf = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdf.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 16f
        paint.color = Color.BLACK

        var y = 50

        canvas.drawText("🐄 Ksheera-Sagara Milk Report", 150f, y.toFloat(), paint)
        y += 40

        canvas.drawText("Top Cow: ${result.topCow.cowId}", 40f, y.toFloat(), paint)
        y += 30

        canvas.drawText("Loss Cow: ${result.lossCow.cowId}", 40f, y.toFloat(), paint)
        y += 30

        canvas.drawText("Total Milk: ${result.totalMilk}", 40f, y.toFloat(), paint)
        y += 30

        canvas.drawText("Average Milk: ${result.averageMilk}", 40f, y.toFloat(), paint)
        y += 40

        canvas.drawText("🤖 AI INSIGHTS:", 40f, y.toFloat(), paint)
        y += 30

        insight.split("\n").forEach {
            canvas.drawText(it, 40f, y.toFloat(), paint)
            y += 25
        }

        pdf.finishPage(page)

        val file = File(context.getExternalFilesDir(null), "Milk_Report.pdf")
        pdf.writeTo(FileOutputStream(file))
        pdf.close()

        return file
    }
}