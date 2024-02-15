package com.example.coffeeshopapp.application

import android.app.Application
import timber.log.Timber

class CoffeeApplication: Application() {

    companion object {
        lateinit var instance: CoffeeApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init(){
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}