package com.silvioapps.githubkotlin.features.shared.fragments

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

open class CustomFragment : Fragment(){
    fun showBackButton(toolbar : Toolbar?, title : String) {
        val currentActivity: AppCompatActivity? = activity as AppCompatActivity
        if(toolbar != null) {
            currentActivity?.setSupportActionBar(toolbar)
        }
        currentActivity?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        currentActivity?.getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        currentActivity?.setTitle(title)
    }
}
