package com.example.employees

import com.example.employees.di.component.AppComponent
import com.example.employees.di.component.DaggerAppComponent
import com.example.employees.di.component.MainActivityComponent
import com.example.employees.di.module.AppModule
import com.example.employees.di.module.DatabaseModule
import com.example.employees.di.module.NetworkModule

class Injector(app: App) {
    val appComponent: AppComponent
    private var mainActivityComponent: MainActivityComponent? = null

    init {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .databaseModule(DatabaseModule(databaseName = "Employees"))
            .networkModule(NetworkModule("https://gitlab.65apps.com/65gb/static/raw/master/"))
            .build()
    }

    fun getMainActivityComponent(): MainActivityComponent {
        if (mainActivityComponent == null) {
            mainActivityComponent = appComponent.plusMainActivityComponent()
        }
        return mainActivityComponent!!
    }

    fun clearMainActivityComponent() {
        mainActivityComponent = null
    }
}