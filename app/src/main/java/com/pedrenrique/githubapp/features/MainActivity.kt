package com.pedrenrique.githubapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pedrenrique.githubapp.R

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        val fragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        fragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}
