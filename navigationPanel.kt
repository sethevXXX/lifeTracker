package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun navPanel(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomAppBar(
            modifier = Modifier
                .height(72.dp)
        ) {
            Button(
                onClick = { navController.navigate("toDoScreen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(3.dp)
            ) {
                Text("to-do")
            }
            Button(
                onClick = { navController.navigate("dailyScreen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(3.dp)
            ) {
                Text("daily")
            }
            Button(
                onClick = { navController.navigate("monsterScreen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(3.dp)
            ) {
                Text("boss")
            }
            Button(
                onClick = { navController.navigate("equipmentScreen") },
                modifier = Modifier
                    .weight(1f)
                    .padding(3.dp)
            ) {
                Text("armory")
            }
        }
    }
}
