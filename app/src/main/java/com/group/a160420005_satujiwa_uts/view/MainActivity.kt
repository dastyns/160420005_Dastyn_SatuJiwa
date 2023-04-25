package com.group.a160420005_satujiwa_uts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.group.a160420005_satujiwa_uts.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentHost) as
                    NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, findViewById<DrawerLayout>(R.id.drawerLayout))
        NavigationUI.setupWithNavController(findViewById<NavigationView>(R.id.navView), navController)

        findViewById<BottomNavigationView>(R.id.bottomNav).setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, findViewById<DrawerLayout>(R.id.drawerLayout))
                || super.onSupportNavigateUp()
    }
}