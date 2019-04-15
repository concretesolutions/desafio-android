package com.dobler.desafio_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        setupActionBarWithNavController(navController!!)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (navController?.popBackStack() != true) {
            super.onBackPressed()
        }
    }

}
