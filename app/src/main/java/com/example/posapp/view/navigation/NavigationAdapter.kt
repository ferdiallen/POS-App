package com.example.posapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posapp.utils.NavRoute
import com.example.posapp.utils.RouteApp
import com.example.posapp.view.add_menu_produk.AddMenuProdukView
import com.example.posapp.view.data_pembelian.DataPembelianView
import com.example.posapp.view.data_penjualan.DataPenjualan
import com.example.posapp.view.detail_order.DetailOrderView
import com.example.posapp.view.detail_produk.DetailProdukView
import com.example.posapp.view.edit_profile.EditProfileView
import com.example.posapp.view.ganti_password.GantiPassword
import com.example.posapp.view.home.HomeView
import com.example.posapp.view.input_pembelian.InputPembelian
import com.example.posapp.view.kasir.KasirView
import com.example.posapp.view.login.LoginView
import com.example.posapp.view.manage_produk.ManageProdukView
import com.example.posapp.view.menu.MenuView
import com.example.posapp.view.order.OrderView
import com.example.posapp.view.pesan_sukses.PesananSuksesView
import com.example.posapp.view.profile.Profile
import com.example.posapp.view.register.RegisterView
import com.example.posapp.view.transaksi.TransaksiOrder

@Composable
fun NavigationAdapter(
    navController: NavHostController,
    showBottomBar: MutableState<Boolean>,
    cart: MutableState<Int>,
    cetak: () -> Unit,
    addButton: () -> Unit
) {
    NavHost(navController = navController, startDestination = RouteApp.Login.route) {

        composable(RouteApp.Login.route) {
            showBottomBar.value = false
            LoginView(navController = navController)
        }

        composable(RouteApp.Register.route) {
            showBottomBar.value = false
            RegisterView(navController = navController)
        }

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
            Profile(navController)
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

        composable(RouteApp.DataPenjualan.route) {
            cart.value = 0
            showBottomBar.value = false
            DataPenjualan(navController = navController)
        }

        composable(RouteApp.DataPembelian.route) {
            cart.value = 0
            showBottomBar.value = false
            DataPembelianView(navController = navController)
        }

        composable(RouteApp.InputPembelian.route) {
            cart.value = 0
            showBottomBar.value = false
            InputPembelian(navController = navController)
        }
        composable(RouteApp.AddMenu.route) {
            cart.value = 0
            showBottomBar.value = false
            AddMenuProdukView(navController = navController) {

            }
        }

        composable(RouteApp.AddFormMenu.route) {
            cart.value = 0
            showBottomBar.value = false
            ManageProdukView(navController = navController)
        }

        composable(RouteApp.GantiPassword.route) {
            cart.value = 0
            showBottomBar.value = false
            GantiPassword(navController = navController)
        }
        composable(RouteApp.EditProfile.route) {
            cart.value = 0
            showBottomBar.value = false
            EditProfileView(navController = navController)
        }

        composable(RouteApp.SetAktif.route) {
            cart.value = 0
            showBottomBar.value = false
            KasirView(navController = navController)
        }
    }
}