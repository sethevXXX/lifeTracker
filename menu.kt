package com.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Menu_Screen(navController: NavHostController)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            onClick = { navController.navigate("toDoScreen") },
            modifier = Modifier
                .padding(12.dp)
                .size(width = 120.dp, height = 45.dp)
        )
        {
            Text(
                text = "to-do",
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { navController.navigate("dailyScreen") },
            modifier = Modifier
                .padding(12.dp)
                .size(width = 120.dp, height = 45.dp)
        )
        {
            Text(
                text = "daily",
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { navController.navigate("monsterScreen") },
            modifier = Modifier
                .padding(12.dp)
                .size(width = 120.dp, height = 45.dp)
        )
        {
            Text(
                text = "boss",
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { navController.navigate("equipmentScreen") },
            modifier = Modifier
                .padding(12.dp)
                .size(width = 120.dp, height = 45.dp)
        )
        {
            Text(
                text = "armory",
                fontSize = 16.sp
            )
        }
    }
}
