package com.example.gitrepositories.modules.pull_requests

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositories.R
import com.example.gitrepositories.modules.ModuleConstants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.pull_requests.*

class PullRequestsActivity : AppCompatActivity() {

    lateinit var viewModel: PullRequestsViewModel
    lateinit var adapter: PullRequestsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_requests)
        toolbar.setNavigationOnClickListener { finish() }

        setUpViewModel()
        setUpAdapter()
        setUpObservers()
    }

    private fun setUpViewModel() {
        val repoName = intent.getStringExtra(ModuleConstants.REPOSITORY_NAME.name)!!
        val repoCreator = intent.getStringExtra(ModuleConstants.REPOSITORY_CREATOR.name)!!
        viewModel = ViewModelProviders.of(this, PullRequestsViewModelFactory(this.application, repoName, repoCreator)).get(PullRequestsViewModel::class.java)
    }

    private fun setUpAdapter() {
        adapter = PullRequestsAdapter(this, viewModel::onPullRequestClick)
        pull_requests_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        pull_requests_list.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.intent.observe(this, Observer {
            startActivity(it)
        })

        viewModel.displayEmptyMessage.observe(this, Observer {
            empty_list_text.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.displayNoBrowserMessage.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })

        viewModel.displayLoadPullRequestsError.observe(this, Observer {
            Snackbar.make(pull_requests_layout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}