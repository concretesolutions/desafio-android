package com.desafioandroid.core.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : AppCompatActivity(){

    fun setToolbar(@StringRes title: Int) {
        include_toolbar.title = getString(title)
    }
}