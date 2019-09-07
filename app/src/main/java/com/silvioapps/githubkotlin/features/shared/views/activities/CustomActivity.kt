package com.silvioapps.githubkotlin.features.shared.views.activities

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

open class CustomActivity : AppCompatActivity() {
    protected fun attachFragment(resId : Int, fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentById = supportFragmentManager.findFragmentById(resId)
        if(fragmentById == null && !isFinishing){
            fragmentTransaction.add(resId, fragment)
            fragmentTransaction.commit()
        }
    }
}
