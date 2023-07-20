package com.example.posapp.utils

sealed class RouteApp(val route:String) {
    object Pesanan:RouteApp("pesanan")
    object DetailOrder:RouteApp("detail_order")
    object DetailProduk:RouteApp("detail_produk")
    object PesananSukses:RouteApp("pesanan_sukses")

}
