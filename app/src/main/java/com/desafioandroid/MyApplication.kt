package com.desafioandroid

import android.app.Application

class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext() = instance as MyApplication
    }

    override fun onCreate() {
        super.onCreate()
    }
}