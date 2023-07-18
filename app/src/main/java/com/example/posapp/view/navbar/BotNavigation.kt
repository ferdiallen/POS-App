package com.example.posapp.view.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.posapp.utils.NavRoute

@Composable
fun BotNavigation(navController:NavController) {
    val menu = listOf(
        NavRoute.Home,
        NavRoute.Menu,
        NavRoute.Transaksi,
        NavRoute.Profile
    )
    
    BottomNavigation(
        Modifier

            .clip(RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ))
            .shadow(14.dp)
            .border(0.1.dp,MaterialTheme.colors.surface.copy(0.1f), RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ))
        ,
        backgroundColor = MaterialTheme.colors.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menu.forEach {item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = { Icon(painterResource(id = item.icon), contentDescription = null) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}