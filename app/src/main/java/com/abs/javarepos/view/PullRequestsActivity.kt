package com.abs.javarepos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abs.javarepos.R
import com.abs.javarepos.model.PullRequest
import com.abs.javarepos.model.Repo
import com.abs.javarepos.view.adapter.PullRequestAdapter
import com.abs.javarepos.view.util.FadeUtils
import com.abs.javarepos.viewmodel.PullRequestsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_pull_requests.*


class PullRequestsActivity : AppCompatActivity() {

    companion object {
        const val REPO_KEY = "repo_key"
    }

    lateinit var repo: Repo
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private lateinit var viewModel: PullRequestsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        repo = intent.getSerializableExtra(REPO_KEY) as Repo

        supportActionBar?.title = repo.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        pullRequestAdapter = PullRequestAdapter(object : PullRequestAdapter.OnItemClickListener {
            override fun onItemClick(pullRequest: PullRequest) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.html_url)))
            }
        })

        rvPullRequests.layoutManager = LinearLayoutManager(this)
        rvPullRequests.adapter = pullRequestAdapter

        viewModel = ViewModelProviders.of(this).get(PullRequestsViewModel::class.java)
        viewModel.fetchPullRequests(repo).observe(this, Observer { items ->
            FadeUtils.fadeOut(progressBar)
            pullRequestAdapter.addItems(items)
        })

        viewModel.networkError.observe(this, Observer { hasNetworkError ->
            FadeUtils.fadeOut(progressBar)
            if (hasNetworkError) {
                Snackbar.make(clPullRequests, getString(R.string.error_get_pull_requests), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.action_reload)) {
                        FadeUtils.fadeIn(progressBar)
                        viewModel.fetchPullRequests(repo)
                    }
                    .show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
