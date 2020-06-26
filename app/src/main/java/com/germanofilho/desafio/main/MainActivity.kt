package com.germanofilho.desafio.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.germanofilho.core.ui.BaseActivity
import com.germanofilho.desafio.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = NavHostFragment.create(R.navigation.app_nav)
        supportFragmentManager.beginTransaction().replace(R.id.container, host).setPrimaryNavigationFragment(host).commit()
    }
}