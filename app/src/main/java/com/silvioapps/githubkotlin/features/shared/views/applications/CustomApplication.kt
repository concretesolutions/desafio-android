package com.silvioapps.githubkotlin.features.shared.views.applications

import android.app.Application
import com.silvioapps.githubkotlin.features.shared.utils.Utils

class CustomApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Utils.fixSSLError(getApplicationContext())
    }
}