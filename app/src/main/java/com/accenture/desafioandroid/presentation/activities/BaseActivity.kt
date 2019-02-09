package com.accenture.desafioandroid.presentation.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.accenture.desafioandroid.R


abstract class BaseActivity : AppCompatActivity() {
    abstract val layout: Int

    abstract fun onCreateView(savedInstanceState: Bundle?)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        onCreateView(savedInstanceState)
    }

    fun pushFragment(fragment: Fragment, TAG: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.frameLayout_fragment_container, fragment, TAG)
            .commit()
    }
}