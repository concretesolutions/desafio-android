package com.abs.javarepos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abs.javarepos.R
import com.abs.javarepos.model.PullRequest
import com.abs.javarepos.model.Repo
import com.abs.javarepos.view.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.activity_pull_requests.*

class PullRequestsActivity : AppCompatActivity() {

    companion object {
        const val REPO_KEY = "repo_key"
    }

    lateinit var repo: Repo
    private lateinit var pullRequestAdapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        repo = intent.getSerializableExtra(REPO_KEY) as Repo

        supportActionBar?.title = getString(R.string.pull_requests_title)

        pullRequestAdapter = PullRequestAdapter(object : PullRequestAdapter.OnItemClickListener {
            override fun onItemClick(pullRequest: PullRequest) {

            }
        })

        rvPullRequests.layoutManager = LinearLayoutManager(this)
        rvPullRequests.adapter = pullRequestAdapter
    }
}
