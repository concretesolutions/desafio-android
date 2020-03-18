package com.rafaelmfer.consultinggithub.mvvm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rafaelmfer.consultinggithub.mvvm.view.HomeActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}