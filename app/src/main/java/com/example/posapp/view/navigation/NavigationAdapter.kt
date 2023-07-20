package com.example.posapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posapp.utils.NavRoute
import com.example.posapp.utils.RouteApp
import com.example.posapp.view.detail_order.DetailOrderView
import com.example.posapp.view.detail_produk.DetailProdukView
import com.example.posapp.view.home.HomeView
import com.example.posapp.view.menu.MenuView
import com.example.posapp.view.order.OrderView
import com.example.posapp.view.pesan_sukses.PesananSuksesView
import com.example.posapp.view.profile.Profile
import com.example.posapp.view.transaksi.TransaksiOrder

@Composable
fun NavigationAdapter(navController: NavHostController,
                      showBottomBar:MutableState<Boolean>,
                      cart:MutableState<Int>,
                      cetak:() -> Unit,
                      addButton:() -> Unit) {
    NavHost(navController = navController, startDestination = NavRoute.Home.route ) {
        composable(NavRoute.Home.route) {
            showBottomBar.value = true
            HomeView(navController = navController) {
                addButton.invoke()
            }
        }
        composable(NavRoute.Menu.route) {
            showBottomBar.value = true
            MenuView(navController) {
                addButton.invoke()
            }
        }
        composable(NavRoute.Transaksi.route) {
            showBottomBar.value = true
            TransaksiOrder(navController)
        }
        composable(NavRoute.Profile.route) {
            showBottomBar.value = true
            Profile()
        }

        composable(RouteApp.Pesanan.route) {
            showBottomBar.value = false
            OrderView(navController = navController)
        }
        
        composable(RouteApp.DetailOrder.route) {
            showBottomBar.value = false
            DetailOrderView(navController = navController)
        }

        composable(RouteApp.DetailProduk.route) {
            cart.value = 0
            showBottomBar.value = false
            DetailProdukView(navController = navController)
        }

        composable(RouteApp.PesananSukses.route) {
            showBottomBar.value = false
            PesananSuksesView() {
                cetak.invoke()
            }
        }
    }
}