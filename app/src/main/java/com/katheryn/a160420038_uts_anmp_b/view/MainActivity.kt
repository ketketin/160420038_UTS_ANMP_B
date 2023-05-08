package com.katheryn.a160420038_uts_anmp_b.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.katheryn.a160420038_uts_anmp_b.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentHost) as
                    NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.kostDetailFragment -> hideNavigation()
                R.id.kostFacilitiesFragment -> hideNavigation()
                R.id.kostPhotosFragment -> hideNavigation()
                R.id.checkOutKostFragment -> hideNavigation()
                else -> showNavigation()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
                || super.onSupportNavigateUp()
    }

    fun showNavigation(){
        bottomNav.visibility = View.VISIBLE
    }

    fun hideNavigation(){
        bottomNav.visibility = View.GONE
    }
}