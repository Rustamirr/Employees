package com.example.employees.di.module

import android.content.Context
import android.widget.ArrayAdapter
import com.example.employees.R
import com.example.employees.database.model.Specialty
import com.example.employees.di.ActivityScope
import com.example.employees.view.list_employee.EmployeeAdapter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class ActivityModule {
    @ActivityScope
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideEmployeeAdapter(): EmployeeAdapter = EmployeeAdapter()

    @Provides
    fun provideArrayAdapter(context: Context): ArrayAdapter<Specialty> =
        ArrayAdapter(context, R.layout.specialty_list_fragment_item, ArrayList<Specialty>())
}