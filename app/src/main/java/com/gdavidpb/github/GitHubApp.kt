package com.gdavidpb.github

import android.app.Application
import com.gdavidpb.github.di.appModule
import org.koin.android.ext.android.startKoin

open class GitHubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}