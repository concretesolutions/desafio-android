package com.uharris.desafio_android.presentation.sections.pull

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.uharris.desafio_android.R
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.presentation.base.BaseActivity
import com.uharris.desafio_android.presentation.base.ViewModelFactory
import com.uharris.desafio_android.presentation.sections.web.WebActivity
import com.uharris.desafio_android.presentation.state.Resource
import com.uharris.desafio_android.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_pull_request.*
import javax.inject.Inject

class PullRequestActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var pullRequestViewModel: PullRequestViewModel

    private lateinit var adapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)

        val repository = intent.getParcelableExtra<Repository>(REPOSITORY_EXTRA)

        supportActionBar?.let {
            it.title = repository.name
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        pullRequestViewModel = ViewModelProviders.of(this, viewModelFactory).get(PullRequestViewModel::class.java)

        pullRequestViewModel.pullRequestsLiveData.observe(this, Observer {
            handleDataState(it)
        })

        pullRequestViewModel.fetchPullRequest(repository.owner.login, repository.name)

        setupUI()
    }

    private fun setupUI() {
        pullRequestRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(mutableListOf()) { pullRequest ->
            WebActivity.startActivity(this, pullRequest)
        }
        pullRequestRecyclerView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun handleDataState(resource: Resource<List<PullRequest>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                hideLoader()
                resource.data?.let {
                    adapter.setItems(it)
                }
            }
            ResourceState.LOADING -> {
                showLoader()
            }
            ResourceState.ERROR -> {
                hideLoader()
                showMessage(resource.message ?: "")
            }
        }
    }


    companion object {

        const val REPOSITORY_EXTRA = "repository_extra"

        fun startActivity(activity: Activity, repository: Repository) {
            activity.startActivity(Intent(activity, PullRequestActivity::class.java).putExtra(REPOSITORY_EXTRA, repository))
        }
    }
}
