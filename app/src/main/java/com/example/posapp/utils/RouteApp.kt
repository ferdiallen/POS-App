package com.example.posapp.utils

sealed class RouteApp(val route:String) {
    object AddFormMenu:RouteApp("add_form_menu")
    object AddMenu:RouteApp("add_menu")
    object InputPembelian:RouteApp("input_pembelian")
    object DataPembelian:RouteApp("data_pembelian")
    object DataPenjualan:RouteApp("data_penjualan")
    object Login:RouteApp("login")
    object Register:RouteApp("register")
    object Pesanan:RouteApp("pesanan")
    object DetailOrder:RouteApp("detail_order")
    object DetailProduk:RouteApp("detail_produk")
    object PesananSukses:RouteApp("pesanan_sukses")

}
