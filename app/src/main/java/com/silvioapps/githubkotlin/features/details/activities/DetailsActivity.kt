package com.silvioapps.githubkotlin.features.details.activities

import android.os.Bundle
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.details.fragments.DetailsFragment
import com.silvioapps.githubkotlin.features.shared.views.activities.CustomActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DetailsActivity : CustomActivity(), HasAndroidInjector {
    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var detailsFragment : DetailsFragment

    override fun onCreate(savedInstanceState : Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if(savedInstanceState == null) {
            detailsFragment.setArguments(intent.getBundleExtra("data"))
            attachFragment(R.id.fragmentContainerView, detailsFragment)
        }
    }

    override fun onSupportNavigateUp() : Boolean{
        onBackPressed()
        return true
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }
}
