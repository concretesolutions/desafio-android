package com.jsouza.repodetail.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.jsouza.repodetail.R
import com.jsouza.repodetail.databinding.ActivityRepoDetailBinding
import com.jsouza.repodetail.domain.model.PullRequests
import com.jsouza.repodetail.presentation.adapter.RepoDetailAdapter
import com.jsouza.repodetail.utils.Constants.Companion.EMPTY_STRING
import com.jsouza.repodetail.utils.PullRequestCalculator
import java.util.Locale
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class RepoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoDetailBinding
    private val viewModel by viewModel<RepoDetailViewModel>()
    private val adapter by inject<RepoDetailAdapter>()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailBinding.inflate(layoutInflater)

        val repoName = intent.getStringExtra(REPO_DETAIL_NAME)
        val userName = intent.getStringExtra(REPO_USER_NAME)
        val repositoryId = intent.getLongExtra(REPO_ID, DEFAULT_REPO_ID)

        setupToolbar(repoName ?: EMPTY_STRING)
        setupRecyclerView()
        viewModel.loadPullRequestsOfRepository(userName, repoName, repositoryId)
        initObserver(repositoryId)

        setContentView(binding.root)
    }

    private fun setupToolbar(
        repositoryName: String
    ) {
        binding.pullRequestToolbarMainDetail.setNavigationOnClickListener { onBackPressed() }
        val repositoryNameToBeDisplayed = repositoryName.toUpperCase(Locale.getDefault())
        binding.repositoryTitleTextViewPullList.text = repositoryNameToBeDisplayed
    }

    private fun setupRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.pullRequestRecyclerViewDetailActivity.addItemDecoration(decoration)
        binding.pullRequestRecyclerViewDetailActivity.setHasFixedSize(true)
        binding.pullRequestRecyclerViewDetailActivity.adapter = adapter
    }

    private fun initObserver(
        repositoryId: Long
    ) {
        viewModel.apply {
            this.returnPullRequestsOnLiveData(repositoryId).observe(this@RepoDetailActivity,
                Observer {
                    it?.let { pullRequests ->
                        adapter.submitList(pullRequests)
                        loadOpenedAndClosedPullRequests(pullRequests)
                    }
                }
            )
        }
    }

    private fun loadOpenedAndClosedPullRequests(
        pullRequests: List<PullRequests>
    ) {
        val openedPullRequests = PullRequestCalculator
            .calculateOpenedPullRequestsCount(pullRequests)
        displayOpenedCount(openedPullRequests)

        val closedPullRequests = PullRequestCalculator
            .calculateClosedPullRequestsCount(pullRequests)
        displayClosedCount(closedPullRequests)
        binding.repositoryColoredViewListItem.visibility = View.VISIBLE
    }

    private fun displayOpenedCount(
        openedPullRequests: Int
    ) {
        val formattedOpenedCount = "$openedPullRequests " +
                getString(R.string.opened_pull_request_status)
        binding.repositoryOpenTextViewListItem.text = formattedOpenedCount
    }

    private fun displayClosedCount(
        closedPullRequests: Int
    ) {
        val formattedClosedCount = "/ $closedPullRequests " +
                getString(R.string.closed_pull_request_status)
        binding.repositoryClosedTextViewListItem.text = formattedClosedCount
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onStop() {
        super.onStop()
        binding.repositoryClosedTextViewListItem.text = EMPTY_STRING
        binding.repositoryOpenTextViewListItem.text = EMPTY_STRING
        adapter.submitList(emptyList())
    }

    companion object {
        const val REPO_DETAIL_NAME = "REPO_DETAIL_NAME"
        const val REPO_USER_NAME = "REPO_USER_NAME"
        const val REPO_ID = "REPO_ID"
        const val DEFAULT_REPO_ID = 0L
    }
}
