package br.com.repository.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.repository.constants.Constants
import br.com.repository.R
import br.com.repository.model.PullRequest
import br.com.repository.model.Repository
import br.com.repository.view.adapter.PullRequestAdapter
import br.com.repository.viewmodel.PullRequestViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PullRequestActivity : BaseActivity() {

    private val pullRequestViewModel: PullRequestViewModel by viewModel()
    private lateinit var repository: Repository
    private val pullRequestAdapter = PullRequestAdapter(onItemClicked())

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun configureView() {
        linearLayoutManager = LinearLayoutManager(this)
        getBinding().include.rvRepository.layoutManager = linearLayoutManager
        getBinding().include.rvRepository.setHasFixedSize(true)
        getBinding().include.rvRepository.adapter = pullRequestAdapter
    }

    override fun init() {
        repository = intent.getParcelableExtra(Constants.OBJECT)
        pullRequestViewModel.callPullRequest(repository)
        pullRequestViewModel.getPull().observe(this, Observer { lPull ->
            pullRequestAdapter.setListPullRequest(lPull!!)
        })

        pullRequestViewModel.showProgress().observe(this, Observer { isShow ->
            if (isShow!!) {
                getBinding().progress.visibility = View.GONE
                getBinding().include.root.visibility = View.VISIBLE
            }
        })

        pullRequestViewModel.showLayoutNoMessage().observe(this, Observer { isShow ->
            if (isShow!!) {
                getBinding().progress.visibility = View.GONE
                getBinding().includeMessage.root.visibility = View.VISIBLE
            }
        })
    }

    private fun onItemClicked(): (PullRequest) -> Unit {
        return { pullRequest ->
            val uri: Uri = Uri.parse(pullRequest.html_url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.activity_base_activity
    override fun getTitleToolbar() = repository.nameRepository
    override fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
