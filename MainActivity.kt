package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project.ui.theme.ProjectTheme
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProjectTheme {
                Surface() {
                    StartApp()
                }
            }
        }
    }
}

@Composable
fun StartApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menuScreen") {

        // main
        composable("menuScreen") {
            Menu_Screen(navController = navController)
        }

        // to-do
        composable("toDoScreen") {
            ToDo_Screen(navController = navController)
        }
        composable("addToDoScreen") {
            AddToDo_Screen(navController = navController)
        }
        composable("editToDoScreen/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            EditToDo_Screen(navController = navController, itemId = itemId)
        }

        // daily
        composable("dailyScreen") {
            Daily_Screen(navController = navController)
        }
        composable("addDailyScreen") {
            AddDaily_Screen(navController = navController)
        }
        composable("editDailyScreen/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            EditDaily_Screen(navController = navController, itemId = itemId)
        }

        // monster
        composable("monsterScreen") {
            Monster_Screen(navController = navController)
        }

        // equipment
        composable("equipmentScreen") {
            Equipment_Screen(navController = navController)
        }
    }
}
