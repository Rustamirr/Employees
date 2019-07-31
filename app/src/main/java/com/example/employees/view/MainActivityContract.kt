package com.example.employees.view

import android.view.MenuItem
import ru.terrakok.cicerone.Navigator

interface MainActivityContract {
    interface View {
        val navigator: Navigator
    }
    interface Presenter {
        fun onResume(view: View)
        fun onPause()
        fun bottomBarItemSelected(menuItem: MenuItem)
    }
}