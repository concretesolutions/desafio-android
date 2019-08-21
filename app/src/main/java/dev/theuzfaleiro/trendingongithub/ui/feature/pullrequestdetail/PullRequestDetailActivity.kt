package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.theuzfaleiro.trendingongithub.R
import kotlinx.android.synthetic.main.activity_pull_request_detail.*

const val PULL_REQUEST_URL = "PULL_REQUEST_URL"

class PullRequestDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_detail)

        val pullRequestUrl = intent.getStringExtra(PULL_REQUEST_URL).orEmpty()

        webViewPullRequestDetail.loadUrl(pullRequestUrl)
    }
}
