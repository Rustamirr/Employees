package com.example.employees.di.module

import com.example.employees.di.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class ActivityModule {
    @ActivityScope
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()
}