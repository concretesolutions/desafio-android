package br.com.concrete.githubconcretechallenge.features.pullrequests.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.concrete.githubconcretechallenge.R
import br.com.concrete.githubconcretechallenge.features.pullrequests.viewmodel.PullRequestsViewModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_pull_requests.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PullRequestsActivity : AppCompatActivity() {

    private val viewModel: PullRequestsViewModel by viewModel()

    private val adapter: PullRequestsAdapter by inject()

    companion object {

        private const val KEY_EXTRA_REPOSITORY_MODEL = "key_extra_repository_model"

        fun start(context: Context, repositoryModel: RepositoryModel) {
            val intent = Intent(context, PullRequestsActivity::class.java)
            intent.putExtra(KEY_EXTRA_REPOSITORY_MODEL, repositoryModel)
            context.startActivity(intent)
        }
    }

    private var repositoryModel: RepositoryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        repositoryModel = intent.extras?.getParcelable(KEY_EXTRA_REPOSITORY_MODEL)

        configActionBar()
        configRecyclerView()
        addObservers()
        createEvents()
        loadData()
    }

    private fun configActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = repositoryModel?.name
    }

    private fun loadData() {
        swipe_refresh_pull_requests.isRefreshing = true
        repositoryModel?.let { repositoryModel ->
            viewModel.loadPullRequests(repositoryModel)
        }
    }

    private fun configRecyclerView() {
        recycler_pull_requests.adapter = adapter
        recycler_pull_requests.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun createEvents() {
        swipe_refresh_pull_requests.setOnRefreshListener {
            repositoryModel?.let { repositoryModel ->
                viewModel.refreshPullRequests(repositoryModel)
                Toast.makeText(this, R.string.invalidating_cache, Toast.LENGTH_LONG).show()
            }
        }

        adapter.onItemClicked = { pullRequest ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.htmlUrl))
            startActivity(browserIntent)
        }
    }

    private fun addObservers() {
        viewModel.liveDataPullRequestList.observe(this, Observer { pullRequestList ->
            adapter.submitList(pullRequestList)
            swipe_refresh_pull_requests.isRefreshing = false
        })

        viewModel.liveDataOpenedClosedPullRequestCount.observe(this, Observer { openedClosedPair ->
            tv_pull_requests_opened.text = openedClosedPair.first.toString()
            tv_pull_requests_closed.text = openedClosedPair.second.toString()
        })

        viewModel.liveDataRequestFailed.observe(this, Observer { hasFailed ->
            if (hasFailed) {
                swipe_refresh_pull_requests.isRefreshing = false
                Snackbar.make(root_layout, R.string.failed_request, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> this.finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
