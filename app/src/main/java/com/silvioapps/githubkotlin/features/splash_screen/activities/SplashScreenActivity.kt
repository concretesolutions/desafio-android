package com.silvioapps.githubkotlin.features.splash_screen.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.silvioapps.githubkotlin.features.list.activities.MainActivity

class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
