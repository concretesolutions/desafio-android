package com.example.eloyvitorio.githubjavapop.ui.pullrequest

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.example.eloyvitorio.githubjavapop.R
import kotlinx.android.synthetic.main.activity_pull_requests_list.*
import kotlinx.android.synthetic.main.error_message_and_load_retry.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val POSITION_OF_LAYOUT_PROGRESS_BAR = 0
private const val POSITION_OF_LAYOUT_PULLREQUEST_LIST = 1
private const val POSITION_OF_LAYOUT_ERROR = 2
private const val POSITION_OF_LAYOUT_PULLREQUEST_EMPTY = 3

class PullRequestsListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PullRequestsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var toolbar: Toolbar
    private lateinit var ownerLogin: String
    private lateinit var repositoryName: String

    private val viewModel: PullRequestsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests_list)

        ownerLogin = this.intent.getStringExtra(OWNER_LOGIN)
        repositoryName = this.intent.getStringExtra(REPOSITORY_NAME)

        setUpToolbar(repositoryName)
        setUpRecyclerView()
        fetchPullRequests()
        setUpObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pullrequest_list, menu)
        return true
    }

    private fun fetchPullRequests() {
        viewFlipperPullRequestList.displayedChild = POSITION_OF_LAYOUT_PROGRESS_BAR
        viewModel.fetchPullRequests(ownerLogin, repositoryName)
    }

    private fun setUpObservables() {
        viewModel.pullRequestList.observe(this, Observer { list ->
            list?.let {
                viewFlipperPullRequestList.displayedChild = POSITION_OF_LAYOUT_PULLREQUEST_LIST
                adapter.addAll(it)

                if (adapter.itemCount == 0) {
                    viewFlipperPullRequestList.displayedChild = POSITION_OF_LAYOUT_PULLREQUEST_EMPTY
                }
            }
        })

        viewModel.error.observe(this, Observer { message ->
            textViewErrorLoadMessage.text = message
            viewFlipperPullRequestList.displayedChild = POSITION_OF_LAYOUT_ERROR
            buttonErrorLoadRetry.setOnClickListener {
                reloadFromError()
            }
        })
    }

    private fun openPullRequestPage(pageUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(pageUrl)
        this.startActivity(Intent.createChooser(intent,
                this.getString(R.string.pullrequestslist_message_for_choose_browser)))
    }

    private fun reloadFromError() {
        fetchPullRequests()
    }

    private fun setUpToolbar(repositoryName: String) {
        toolbar = findViewById(R.id.toolbarPullRequestList)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = repositoryName
    }

    private fun setUpRecyclerView() {
        adapter = PullRequestsAdapter { repositoryHtmlUrl ->
            openPullRequestPage(repositoryHtmlUrl)
        }
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = recyclerViewPullRequestsList
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    companion object {
        private const val OWNER_LOGIN = "login"
        private const val REPOSITORY_NAME = "repositoryName"

        fun newIntent(context: Context, login: String, repositoryName: String): Intent {
            return Intent(context, PullRequestsListActivity::class.java).apply {
                putExtra(OWNER_LOGIN, login)
                putExtra(REPOSITORY_NAME, repositoryName)
            }
        }
    }
}
