package com.example.employees.di.component

import com.example.employees.di.ActivityScope
import com.example.employees.di.module.ActivityModule
import dagger.Subcomponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainActivityComponent {
    fun getCicerone(): Cicerone<Router>
}