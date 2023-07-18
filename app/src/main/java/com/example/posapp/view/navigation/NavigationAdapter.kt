package com.example.posapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posapp.utils.NavRoute
import com.example.posapp.view.home.HomeView
import com.example.posapp.view.menu.MenuView
import com.example.posapp.view.profile.Profile
import com.example.posapp.view.transaksi.TransaksiOrder

@Composable
fun NavigationAdapter(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Home.route ) {
        composable(NavRoute.Home.route) {
            HomeView()
        }
        composable(NavRoute.Menu.route) {
            MenuView()
        }
        composable(NavRoute.Transaksi.route) {
            TransaksiOrder()
        }
        composable(NavRoute.Profile.route) {
            Profile()
        }
    }
}