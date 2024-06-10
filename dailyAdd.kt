package com.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import android.util.Log

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
fun AddDaily_Screen(navController: NavHostController) {

    val context = LocalContext.current
    val fileName = fileNameDaily

    var taskText by remember { mutableStateOf("") }
    var dateText by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    // MAIN
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Add New Task",
            fontSize = 26.sp
        )
        // TEXT FIELDS
        TextField(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 80.dp),
            value = taskText,
            onValueChange = { newText -> taskText = newText },
            placeholder = { Text("task") }
        )
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = dateText,
            onValueChange = { newText -> dateText = newText },
            placeholder = { Text("date") }
        )

    }
    // BUTTON
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                saveDataToFile(context, fileName, taskText, dateText, isChecked)
                navController.navigate("dailyScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Add Daily Task",
                fontSize = 20.sp
            )
        }
    }
}
