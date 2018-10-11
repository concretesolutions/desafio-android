package com.diegoferreiracaetano.github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.diegoferreiracaetano.github.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        NavigationUI.setupActionBarWithNavController(this, host.navController)

    }


    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}
