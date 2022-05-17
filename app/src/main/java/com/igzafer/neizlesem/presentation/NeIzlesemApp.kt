package com.igzafer.neizlesem.presentation

import android.app.Application
import com.igzafer.neizlesem.data.util.WifiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NeIzlesemApp : Application() {
    companion object {
        lateinit var instance:  NeIzlesemApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupServices()
    }

    private fun setupServices() {
        WifiService.instance.initializeWithApplicationContext(this)
    }
}