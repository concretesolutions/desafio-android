package com.accenture.desafioandroid.presentation.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.presentation.fragment.HomeFragment

class MainActivity : BaseActivity() {
    override val layout: Int
        get() = R.layout.activity_main

    var homeFragment: Fragment? = null

    override fun onResume() {
        super.onResume()
    }


    override fun onCreateView(savedInstanceState: Bundle?) {
        homeFragment = HomeFragment()
        pushFragment(homeFragment!!, "HomeFragment")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

    }
}
