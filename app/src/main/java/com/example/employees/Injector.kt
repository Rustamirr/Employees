package com.example.employees

import com.example.employees.di.component.AppComponent
import com.example.employees.di.component.DaggerAppComponent
import com.example.employees.di.module.AppModule
import com.example.employees.di.module.DatabaseModule

class Injector(app: App) {
    val appComponent: AppComponent

    init {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .databaseModule(DatabaseModule(databaseName = "Employees"))
            .build()
    }
}