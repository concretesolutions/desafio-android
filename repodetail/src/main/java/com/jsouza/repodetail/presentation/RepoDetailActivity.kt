package com.jsouza.repodetail.presentation

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.jsouza.repodetail.R
import com.jsouza.repodetail.data.remote.response.PullsResponse
import com.jsouza.repodetail.databinding.ActivityRepoDetailBinding
import com.jsouza.repodetail.presentation.adapter.RepoDetailAdapter
import com.jsouza.repodetail.utils.Constants.Companion.ONE_ITEM
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RepoDetailActivity : AppCompatActivity() {

    companion object {
        const val REPO_DETAIL_NAME = "REPO_DETAIL_NAME"
        const val REPO_USER_NAME = "REPO_USER_NAME"
    }

    private lateinit var binding: ActivityRepoDetailBinding
    private val viewModel by viewModel<RepoDetailViewModel>()
    private val adapter by inject<RepoDetailAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailBinding.inflate(layoutInflater)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_TITLE
        supportActionBar?.title = "Pull Requests"

        val repoName = intent.getStringExtra(REPO_DETAIL_NAME)
        val userName = intent.getStringExtra(REPO_USER_NAME)

        setupRecyclerView()

        viewModel.fetchPullRequests(userName, repoName)
        initObserver()

        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.repositoryRecyclerViewDetailActivity.addItemDecoration(decoration)
        binding.repositoryRecyclerViewDetailActivity.setHasFixedSize(true)
        binding.repositoryRecyclerViewDetailActivity.adapter = adapter
    }

    private fun initObserver() {
        viewModel.apply {
            this.returnPulls?.observe(this@RepoDetailActivity,
                Observer { adapter.submitList(it) ; loadOpenedAndClosedPullRequests(it) })
        }
    }

    private fun loadOpenedAndClosedPullRequests(
        pullRequests: List<PullsResponse>
    ) {
        calculateOpenedPullRequestsCount(pullRequests)
        calculateClosedPullRequestsCount(pullRequests)
    }

    private fun calculateOpenedPullRequestsCount(
        pullRequests: List<PullsResponse>
    ) {
        val openedPullsList = arrayListOf<Int>()
        for (element in pullRequests) {
            element.state.let { pullRequestStatus ->
                when (pullRequestStatus) {
                    getString(R.string.open_pull_request_status) -> openedPullsList.add(ONE_ITEM)
                    else -> {}
                }
            }
        }
        displayOpenedCount(openedPullsList)
    }

    private fun displayOpenedCount(
        openedPullsList: ArrayList<Int>
    ) {
        val openedCount = openedPullsList.sum()
        val formattedOpenedCount = "$openedCount ${getString(R.string.open_pull_request_status)}"
        binding.repositoryOpenTextViewListItem.text = formattedOpenedCount
    }

    private fun calculateClosedPullRequestsCount(
        pullRequests: List<PullsResponse>
    ) {
        val closedPullsList = arrayListOf<Int>()
        for (element in pullRequests) {
            element.state.let { pullRequestStatus ->
                when (pullRequestStatus) {
                    getString(R.string.closed_pull_request_status) -> closedPullsList.add(ONE_ITEM)
                    else -> {}
                }
            }
        }
        displayClosedCount(closedPullsList)
    }

    private fun displayClosedCount(
        closedPullsList: ArrayList<Int>
    ) {
        val closedCount = closedPullsList.sum()
        val formattedClosedCount = "/ $closedCount ${getString(R.string.closed_pull_request_status)}"
        binding.repositoryClosedTextViewListItem.text = formattedClosedCount
    }

    override fun onPause() {
        super.onPause()
        initObserver()
    }
}
