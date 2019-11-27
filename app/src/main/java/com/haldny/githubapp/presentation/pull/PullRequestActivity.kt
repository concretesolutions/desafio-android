package com.haldny.githubapp.presentation.pull

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.haldny.githubapp.R
import com.haldny.githubapp.common.extension.addColorSpecificText
import com.haldny.githubapp.common.observeResource
import com.haldny.githubapp.domain.model.ResponsePullRequest
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PullRequestActivity : AppCompatActivity() {

    private var owner: String = ""
    private var repository: String = ""
    private var stateOpen: Int = 0
    private var stateClosed: Int = 0

    companion object {
        private const val STATE_OPEN = "open"
        private const val STATE_CLOSED = "closed"
    }

    fun setToolbar(@StringRes title: Int, showHomeAsUp: Boolean) {
        my_toolbar.title = getString(title)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private val viewModel by viewModel<PullRequestViewModel> {
        parametersOf(owner, repository)
    }

    private val pullRequestAdapter by lazy {
        PullRequestAdapter { pullRequestResponse ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pullRequestResponse.url)))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setToolbar(title = R.string.title_toolbar_pull_requests, showHomeAsUp = true)

        catchIntent()

        initViewModel()
        iniUi()
    }

    private fun catchIntent() {
        intent?.let {
            owner = it.getStringExtra("owner")
            repository = it.getStringExtra("repository")
        }
    }

    private fun initViewModel() {
        viewModel.getPullRequests().observeResource(this,
            onSuccess = {
                populateList(it)
                populateStatePullRequest(it)
                showSuccess()
            },
            onLoading = {
                showLoading()
            },
            onError = {
                showError()
            }
        )
    }

    private fun iniUi() {
        with(pullRequestRecyclerView) {
            this.adapter = pullRequestAdapter
            val linearLayoutManager = LinearLayoutManager(this@PullRequestActivity)
            this.layoutManager = linearLayoutManager
        }

        swipeRefresh()
    }

    private fun populateList(pullRequestList: List<ResponsePullRequest>) {
        pullRequestAdapter.addList(pullRequestList)
        showListEmpty(pullRequestList)
    }

    private fun populateStatePullRequest(pullRequestList: List<ResponsePullRequest>) {
        pullRequestList.forEach {
            when(it.state){
                STATE_OPEN -> stateOpen++
                STATE_CLOSED -> stateClosed++
            }
        }

        val open = getString(R.string.open_pull_requests, stateOpen)
        val closed = getString(R.string.closed_pull_requests, stateClosed)

        tv_pull_request_total_state.text = getString(R.string.open_and_closed_pull_requests,
            open, closed)
            .addColorSpecificText(this, R.color.colorOrange, open)
    }

    private fun refresh() {
        stateOpen = 0
        stateClosed = 0
        pullRequestAdapter.clearList()
        viewModel.refreshViewModel()
    }

    private fun swipeRefresh() {
        pullRequestRefresh.setOnRefreshListener {
            Handler().postDelayed({
                refresh()

                pullRequestRefresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun showSuccess() {
        pullRequestRecyclerView.visibility = View.VISIBLE
        pullrequestTotalState.visibility = View.VISIBLE
        pullRequestLoading.visibility = View.GONE
    }

    private fun showLoading() {
        pullRequestLoading.visibility = View.VISIBLE
    }

    private fun showError() {
        pullRequestLoading.visibility = View.GONE
        pullRequestRecyclerView.visibility = View.GONE
        pullrequestTotalState.visibility = View.GONE
    }

    private fun showListEmpty(pullRequestList: List<ResponsePullRequest>) {
        val listIsEmpty = pullRequestList.isEmpty()
        val listEmptyVisibilityGone = if (listIsEmpty) View.GONE else View.VISIBLE

        pullRequestRefresh.visibility = listEmptyVisibilityGone
        pullrequestTotalState.visibility = listEmptyVisibilityGone
    }
}