package com.example.project

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.project.ui.theme.ogreGreen

@Composable
fun Monster_Screen(navController: NavHostController) {

    val context: Context = LocalContext.current
    var maxHealth = Monster.HP
    var currentHealth = Monster.getBossHP( context )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // IMAGE
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.mipmap.monster_image_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            // HP BAR
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth()
                        .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                )
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(currentHealth.toFloat() / maxHealth.toFloat())
                        .background(color = ogreGreen, shape = RoundedCornerShape(8.dp))
                )
                Text(
                    text = "$currentHealth/$maxHealth HP",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    // BUTTON
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                navController.navigate("menuScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = "menu",
                fontSize = 15.sp
            )
        }
    }

    navPanel(navController = navController)
}
