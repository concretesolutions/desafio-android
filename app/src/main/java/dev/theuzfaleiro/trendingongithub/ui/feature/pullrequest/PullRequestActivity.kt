package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.adapter.PullRequestAdapter
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel.PullRequestViewModel
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel.PullRequestViewModelFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PULL_REQUEST_URL
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PullRequestDetailActivity
import kotlinx.android.synthetic.main.activity_pull_request.*
import javax.inject.Inject

const val PULL_REQUEST_REPOSITORY_NAME = "PULL_REQUEST_REPOSITORY_NAME"
const val PULL_REQUEST_OWNER_NAME = "PULL_REQUEST_OWNER_NAME"

class PullRequestActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var pullRequestViewModelFactory: PullRequestViewModelFactory

    private val pullRequestViewModel: PullRequestViewModel by lazy {
        ViewModelProviders.of(this, pullRequestViewModelFactory)
            .get(PullRequestViewModel::class.java)
    }

    private val pullRequestAdapter: PullRequestAdapter by lazy {
        PullRequestAdapter {
            startActivity(
                Intent(this, PullRequestDetailActivity::class.java).apply {
                    putExtras(
                        bundleOf(
                            PULL_REQUEST_URL to it.url
                        )
                    )
                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        val username = intent.getStringExtra(PULL_REQUEST_OWNER_NAME).orEmpty()
        val repositoryName = intent.getStringExtra(PULL_REQUEST_REPOSITORY_NAME).orEmpty()

        setUpRecyclerView()

        pullRequestViewModel.getRepositories().observe(this, Observer {
            pullRequestAdapter.submitList(it)
        })

        pullRequestViewModel.fetchPullRequests(username, repositoryName)
    }

    private fun setUpRecyclerView() {
        with(recyclerViewPullRequest) {
            adapter = pullRequestAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
