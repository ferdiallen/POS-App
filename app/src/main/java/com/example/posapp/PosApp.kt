package com.example.posapp

import android.app.Application
import com.mazenrashed.printooth.Printooth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PosApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Printooth.init(this)
    }
}