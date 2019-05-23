package com.uharris.desafio_android.presentation.sections.web

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uharris.desafio_android.R
import com.uharris.desafio_android.domain.models.PullRequest
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        setSupportActionBar(toolbar)

        val pullRequest = intent.getParcelableExtra<PullRequest>(PULL_REQUEST_EXTRA)

        supportActionBar?.let {
            it.title = pullRequest.title
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        webView.loadUrl(pullRequest.url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        const val PULL_REQUEST_EXTRA = "repository_extra"

        fun startActivity(activity: Activity, pullRequest: PullRequest) {
            activity.startActivity(Intent(activity, WebActivity::class.java).putExtra(PULL_REQUEST_EXTRA, pullRequest))
        }
    }
}
