package com.example.posapp.utils

import com.example.posapp.R

sealed class NavRoute(val route:String,val icon:Int) {
    object Home:NavRoute("home", R.drawable.home)
    object Menu:NavRoute("menu",R.drawable.menu)
    object Transaksi:NavRoute("transaksi",R.drawable.transaksi)
    object Profile:NavRoute("profile",R.drawable.profile)
}
