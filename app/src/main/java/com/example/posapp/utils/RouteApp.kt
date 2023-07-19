package com.example.posapp.utils

sealed class RouteApp(val route:String) {
    object Pesanan:RouteApp("pesanan")
}
