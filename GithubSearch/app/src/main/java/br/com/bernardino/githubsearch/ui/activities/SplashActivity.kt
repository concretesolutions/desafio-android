package br.com.bernardino.githubsearch.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.bernardino.githubsearch.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private val splashDelay: Long = 3000

    private val splashActivityJob = SupervisorJob()
    private val activityScope = CoroutineScope(splashActivityJob + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            delay(splashDelay)

            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        splashActivityJob.cancel()
        super.onPause()

    }
}