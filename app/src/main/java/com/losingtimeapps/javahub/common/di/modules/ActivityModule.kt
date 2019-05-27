package com.losingtimeapps.javahub.common.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.losingtimeapps.javahub.application.JavaHubApplication
import com.losingtimeapps.javahub.common.di.qualifiers.ForActivity
import com.losingtimeapps.javahub.common.di.qualifiers.ForApplication
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ForActivity
    @Provides
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }

}

