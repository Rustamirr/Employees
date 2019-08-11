package com.example.employees.utils

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveToFile(bitmap: Bitmap, fileName: String): String? {
    val file = File(Environment.getExternalStorageDirectory(),"$fileName.png")
    val outputStream = FileOutputStream(file)
    return try {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        file.absolutePath
    } catch (e: IOException) { null }
}