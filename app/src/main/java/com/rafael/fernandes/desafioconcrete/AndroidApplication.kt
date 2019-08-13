package com.rafael.fernandes.desafioconcrete

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.rafael.fernandes.data.modules.RoomModule
import com.rafael.fernandes.desafioconcrete.injection.Injectable
import com.rafael.fernandes.desafioconcrete.injection.components.AppComponent
import com.rafael.fernandes.desafioconcrete.injection.components.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AndroidApplication : Application(), HasActivityInjector, Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent

    init {
        registerActivityLifecycleCallbacks(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setupDagger()
    }

    open fun createComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .application(this)
            .roomModule(RoomModule(this))
            .build()
    }

    private fun setupDagger() {
        appComponent = createComponent()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityAndroidInjector
    override fun onActivityCreated(activity: Activity?, p1: Bundle?) = handleActivity(activity)
    override fun onActivityPaused(p0: Activity?) {}
    override fun onActivityResumed(p0: Activity?) {}
    override fun onActivityStarted(p0: Activity?) {}
    override fun onActivityDestroyed(p0: Activity?) {}
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}
    override fun onActivityStopped(p0: Activity?) {}

    private fun handleActivity(activity: Activity?) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        fragment: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        if (fragment is Injectable) {
                            AndroidSupportInjection.inject(fragment)
                        }
                    }
                }, true)
        }
    }
}