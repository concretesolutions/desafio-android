package com.example.githubtest.features.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.githubtest.R
import com.example.githubtest.features.repository.RepositoryActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                jump(null)
            }
        }, 5000)
        val image = findViewById(R.id.imageSplash) as ImageView
        val animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.animation_blink
        )
        image.startAnimation(animation)
    }

    fun jump(view: View?) {
        startActivity(Intent(this, RepositoryActivity::class.java))
        finish()
    }
}

