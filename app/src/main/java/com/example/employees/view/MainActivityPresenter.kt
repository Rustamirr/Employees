package com.example.employees.view

import android.view.MenuItem
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.employees.App
import com.example.employees.R
import com.example.employees.utils.EmployeeListScreen
import ru.terrakok.cicerone.Screen
import com.example.employees.utils.EmployeeScreen
import com.example.employees.utils.SpecialtyListScreen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class MainActivityPresenter: ViewModel(), MainActivityContract.Presenter {
    private var bottomNavigationViewCurrentItemId = R.id.bottom_bar_employees
    private val cicerone: Cicerone<Router> = App.instance.injector.getMainActivityComponent().getCicerone()

    init {
        // start screen
        showScreen(EmployeeListScreen())
    }

    override fun onResume(view: MainActivityContract.View){
        cicerone.navigatorHolder.setNavigator(view.navigator)
    }

    override fun onPause(){
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onCleared() {
        super.onCleared()
        App.instance.injector.clearMainActivityComponent()
    }

    override fun bottomBarItemSelected(menuItem: MenuItem) {
        if (bottomNavigationViewCurrentItemId != menuItem.itemId) {
            bottomNavigationViewCurrentItemId = menuItem.itemId

            when (menuItem.itemId) {
                R.id.bottom_bar_employees -> showScreen(EmployeeListScreen())
                R.id.bottom_bar_specialties -> showScreen(SpecialtyListScreen())
                else -> throw IllegalArgumentException("Unknown bottom bar menu item id")
            }
        }
    }

    private fun showScreen(screen: Screen) {
        cicerone.router.newRootScreen(screen)
    }
}