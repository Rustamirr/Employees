package com.example.employees

import android.app.Application

class App: Application() {
    lateinit var injector: Injector
    private set

    companion object {
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        injector = Injector(this)
    }
}