package com.example.employees.di.module

import android.content.Context
import androidx.room.Room
import com.example.employees.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val databaseName: String) {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .build()
}