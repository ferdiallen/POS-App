package com.example.posapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.posapp.view.navbar.BotNavigation
import com.example.posapp.view.navigation.NavigationAdapter

@Composable
fun MainView() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Surface(
                color = Color.Transparent,
                elevation = 18.dp,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ) {
                BotNavigation(navController = navController)
            }
        },
        backgroundColor = Color.Transparent
    ) {

    Surface(
        Modifier
            .padding(it)
            .fillMaxSize()
        ,
        color = Color.Transparent) {
            NavigationAdapter(navController = navController)

    }



    }
}
