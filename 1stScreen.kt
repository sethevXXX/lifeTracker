package com.example.project

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.project.ui.theme.lightGray
import com.example.project.ui.theme.lightRed
import java.io.BufferedReader
import java.io.File
import java.io.IOException

@Composable
fun MainScreen(navController: NavHostController) {

    val context: Context = LocalContext.current
    var numberOfCards by remember { mutableStateOf(1) }
    var cardTexts by remember { mutableStateOf<List<String>>(emptyList()) }

    // LOAD DATA FROM FILE TO CARDS
    LaunchedEffect(key1 = numberOfCards) {
        val fileName = myFileName
        try {
            val file = File(context.filesDir, fileName)
            val bufferedReader: BufferedReader = file.bufferedReader()
            cardTexts = bufferedReader.readLines()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // MAIN
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "My Tasks",
            fontSize = 26.sp
        )

        // DYNAMIC CARDS
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(cardTexts.size) { index ->
                val (task, date, isChecked) = readDataFromFile(context, index)
                val xd = {if (isChecked) 1 else 0}
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("thirdScreen/$index")
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (isChecked) lightGray else lightRed)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Checkbox(
                                checked = if (isChecked) true else false,
                                onCheckedChange = {
                                    modifyDataInFile(context, index, task, date, !isChecked)
                                    cardTexts = cardTexts.toMutableList().also { it[index] = isChecked.toString() }
                                }
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "$task $date",
                                fontSize = 22.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
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
                navController.navigate("secondScreen")
                numberOfCards++
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "NEW",
                fontSize = 20.sp
            )
        }
    }
}
