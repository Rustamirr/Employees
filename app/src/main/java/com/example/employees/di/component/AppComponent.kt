package com.example.employees.di.component

import com.example.employees.di.module.AppModule
import com.example.employees.di.module.DatabaseModule
import com.example.employees.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

}