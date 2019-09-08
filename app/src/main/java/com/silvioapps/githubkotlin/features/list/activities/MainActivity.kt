package com.silvioapps.githubkotlin.features.list.activities

import com.silvioapps.githubkotlin.features.shared.views.activities.CustomActivity
import android.os.Bundle
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.features.list.fragments.MainFragment

class MainActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            attachFragment(R.id.frameLayout, MainFragment())
        }
    }
}
