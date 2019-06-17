package br.com.arthur.githubapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.arthur.githubapp.R
import br.com.arthur.githubapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        bind = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        bind.appImage.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}
