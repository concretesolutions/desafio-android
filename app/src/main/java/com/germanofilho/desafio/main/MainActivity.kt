package com.germanofilho.desafio.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.germanofilho.core.ui.BaseActivity
import com.germanofilho.desafio.R
import com.germanofilho.home.presentation.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setContentLayout(HomeFragment(), "")
    }


    private fun setContentLayout(fragment: Fragment, tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment, tag).commit()
    }
}