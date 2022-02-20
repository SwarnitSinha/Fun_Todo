package com.fun_todo.memesandtodocombine

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fun_todo.memesandtodocombine.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    USE VIEW Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        take a reference of bottom nav view
        val navView : BottomNavigationView = binding.bottomNavView

//      NAV-CONTROLLER -> An object that manages app navigation within a NavHost
//      We are adding navController in activity because navHostFragment is in activity
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        val navController = findNavController(R.id.nav_host_fragment)

//App Bar Configuration
        val displayMode = resources.configuration.orientation
        if (displayMode == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar!!.hide()
        }
        else{
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.memeFragment, R.id.todoFragment
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        navView.setupWithNavController(navController)


    }

}