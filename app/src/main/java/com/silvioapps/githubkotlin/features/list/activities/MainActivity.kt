package com.silvioapps.githubkotlin.features.list.activities

import com.silvioapps.githubkotlin.features.shared.views.activities.CustomActivity
import android.os.Bundle
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.list.fragments.MainFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : CustomActivity(), HasAndroidInjector {
    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            attachFragment(R.id.fragmentContainerView, mainFragment)
        }
    }

    override fun androidInjector(): AndroidInjector<Any>{
        return fragmentDispatchingAndroidInjector
    }
}
