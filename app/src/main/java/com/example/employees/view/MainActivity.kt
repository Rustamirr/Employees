package com.example.employees.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.example.employees.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    @BindView(R.id.activity_main_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activity_main_bottom_navigation_view)
    lateinit var bottomNavigationView: BottomNavigationView

    override lateinit var navigator: Navigator
    private lateinit var presenter: MainActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        navigator = SupportAppNavigator(this, R.id.activity_main_frame_layout)
        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            presenter.bottomBarItemSelected(menuItem)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}