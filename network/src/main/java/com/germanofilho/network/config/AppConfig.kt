package com.germanofilho.network.config

import android.content.Context

object AppConfig {

    lateinit var appContext: Context

    fun setContext(context: Context){
        this.appContext = context
    }
}