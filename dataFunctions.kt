package com.example.project

import android.content.Context
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val myFileName = "data6.txt"

// SAVE DATA TO FILE
fun saveDataToFile(context: Context, task: String, date: String, isChecked:Boolean) {
    if (task.isEmpty() || date.isEmpty()) {
        Toast.makeText(context, "not enough data", Toast.LENGTH_SHORT).show()
        return
    }

    val fileName = myFileName
    val file = File(context.filesDir, fileName)

    try {
        if (!file.exists()) {
            file.createNewFile()
        }

        val fileOutputStream = FileOutputStream(file, true)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)

        val isCheckedValue = if (isChecked) 1 else 0
        outputStreamWriter.write("$task#$date#$isCheckedValue\n")

        outputStreamWriter.close()
        fileOutputStream.close()

        Toast.makeText(context, "Data Saved to File", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error Saving Data to File", Toast.LENGTH_SHORT).show()
    }

}

// READ DATA FROM FILE
fun readDataFromFile(context: Context, index: Int): Triple<String, String, Boolean> {
    val fileName = myFileName
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val fileInputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var currentLineIndex = 0
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                if (currentLineIndex == index) {
                    val dataParts = line?.split("#".toRegex())
                    if (dataParts != null && dataParts.size >= 2) {
                        val task = dataParts[0]
                        val date = dataParts[1]
                        val isChecked = dataParts.getOrNull(2)?.toInt() == 1
                        return Triple(task, date, isChecked)
                    }
                }
                currentLineIndex++
            }

            bufferedReader.close()
            inputStreamReader.close()
            fileInputStream.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error reading data from file", Toast.LENGTH_SHORT).show()
    }

    return Triple("", "", false)
}

// MODIFY DATA IN FILE BY INDEX
fun modifyDataInFile(context: Context, index: Int, newTask: String, newDate: String, isChecked: Boolean): Boolean {
    val fileName = myFileName
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val lines = file.readLines().toMutableList()

            if (index >= 0 && index < lines.size) {
                val updatedLine = "$newTask#$newDate#${if (isChecked) 1 else 0}"
                lines[index] = updatedLine

                val fileWriter = FileWriter(file, false)
                val bufferedWriter = BufferedWriter(fileWriter)

                for (line in lines) {
                    bufferedWriter.write(line)
                    bufferedWriter.newLine()
                }

                bufferedWriter.close()
                fileWriter.close()

                return true
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error modifying data in file", Toast.LENGTH_SHORT).show()
    }

    return false
}

// DELETE DATA FROM FILE BY INDEX
fun deleteDataFromFile(context: Context, index: Int): Boolean {
    val fileName = myFileName
    val file = File(context.filesDir, fileName)

    try {
        if (file.exists()) {
            val lines = file.readLines().toMutableList()

            if (index >= 0 && index < lines.size) {
                lines.removeAt(index)

                val fileWriter = FileWriter(file, false)
                val bufferedWriter = BufferedWriter(fileWriter)

                for (line in lines) {
                    bufferedWriter.write(line)
                    bufferedWriter.newLine()
                }

                bufferedWriter.close()
                fileWriter.close()

                Toast.makeText(context, "Data deleted from file", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error deleting data from file", Toast.LENGTH_SHORT).show()
    }

    Toast.makeText(context, "Error deleting data from file", Toast.LENGTH_SHORT).show()
    return false
}
