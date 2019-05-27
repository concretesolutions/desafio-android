package com.losingtimeapps.javahub.application

import android.app.Application

class JavaHubApplication : Application() {

    private var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        getComponent().inject(this)
    }

    fun getComponent(): ApplicationComponent {
        if (component == null) {
            component = DaggerApplicationComponent.builder().application(this).build()
        }
        return component as ApplicationComponent
    }

}
