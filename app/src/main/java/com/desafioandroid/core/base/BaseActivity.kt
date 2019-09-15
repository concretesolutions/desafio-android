package com.desafioandroid.core.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : AppCompatActivity(){

    fun setToolbar(@StringRes title: Int, showHomeAsUp: Boolean) {
        include_toolbar.title = getString(title)
        setSupportActionBar(include_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}