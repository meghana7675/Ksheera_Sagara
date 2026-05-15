package com.example.ksheera_sagara.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream

fun createMonthlyPdf(
    context: Context,
    income: Double,
    expense: Double,
    profit: Double
) {

    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(300, 400, 1).create()
    val page = document.startPage(pageInfo)

    val canvas = page.canvas
    val paint = Paint()

    canvas.drawText("Ksheera Sagara Report", 10f, 20f, paint)
    canvas.drawText("Income: ₹$income", 10f, 60f, paint)
    canvas.drawText("Expense: ₹$expense", 10f, 100f, paint)
    canvas.drawText("Profit: ₹$profit", 10f, 140f, paint)

    document.finishPage(page)

    val file = File(context.getExternalFilesDir(null), "monthly_report.pdf")
    document.writeTo(FileOutputStream(file))

    document.close()
}