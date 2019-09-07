package com.silvioapps.githubkotlin.features.details.activities

import android.os.Bundle
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.details.fragments.DetailsFragment
import com.silvioapps.githubkotlin.features.shared.views.activities.CustomActivity

class DetailsActivity : CustomActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if(savedInstanceState == null) {
            val detailsFragment = DetailsFragment()
            detailsFragment.setArguments(intent.getBundleExtra("data"))

            attachFragment(R.id.frameLayout, detailsFragment)
        }
    }

    override fun onSupportNavigateUp() : Boolean{
        onBackPressed()
        return true
    }
}
