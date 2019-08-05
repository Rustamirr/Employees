package com.example.employees.view

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.example.employees.App
import com.example.employees.R
import com.example.employees.utils.EmployeeListScreen
import com.example.employees.utils.SpecialtyListScreen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

class MainActivityPresenter: ViewModel(), MainActivityContract.Presenter {
    private var bottomNavigationViewCurrentItemId = R.id.bottom_bar_employees
    private val cicerone: Cicerone<Router>

    init {
        cicerone = App.instance.injector.getMainActivityComponent().getCicerone()
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