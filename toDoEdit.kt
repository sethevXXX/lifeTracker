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

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
fun EditToDo_Screen(navController: NavHostController, itemId: Int) {

    val context = LocalContext.current
    val fileName = fileNameToDo

    val (subject, degree, isChecked) = readDataFromFile(context, fileName, itemId)
    var taskText by remember { mutableStateOf(subject) }
    var dateText by remember { mutableStateOf(degree) }
    var checked by remember { mutableStateOf(isChecked) }


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
            text = "Edit",
            fontSize = 26.sp
        )
        //TEXT FIELDS
        TextField(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 80.dp),
            value = taskText,
            onValueChange = { newText -> taskText = newText }
        )
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = dateText,
            onValueChange = { newText -> dateText = newText }
        )
    }
    // BUTTONS
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // BUTTON TO SAVE
        Button(
            onClick = {
                modifyDataInFile(context, fileName, itemId, taskText, dateText, checked)
                navController.navigate("toDoScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "SAVE",
                fontSize = 20.sp
            )
        }
        // BUTTON TO DELETE DATA
        Button(
            onClick = {
                deleteDataFromFile(context, fileName, itemId)
                navController.navigate("toDoScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "DELETE",
                fontSize = 20.sp
            )
        }
    }
}


