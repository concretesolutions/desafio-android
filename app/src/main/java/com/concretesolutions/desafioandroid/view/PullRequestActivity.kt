package com.concretesolutions.desafioandroid.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.adapters.PullRequestAdapter
import com.concretesolutions.desafioandroid.databinding.ActivityPullRequestViewBinding
import com.concretesolutions.desafioandroid.model.PullRequest
import com.concretesolutions.desafioandroid.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pull_request_view.*

class PullRequestActivity : AppCompatActivity() {

    private val PULLSPARCEL: String = "pullrequestparcel"
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private val RVPOSITION: String = "rvpullrequest"
    private var loadFinished: Boolean = false
    private lateinit var ownerName: String
    private lateinit var reposName: String
    private lateinit var pullRequestViewModel: PullRequestViewModel
    private lateinit var bindig: ActivityPullRequestViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindig = DataBindingUtil.setContentView(this, R.layout.activity_pull_request_view)


        initExtras()
        initAdapters()
        initView()
        loadData()

    }

    private fun loadData() {
        pullRequestViewModel.loadPulls(ownerName, reposName)
    }

    private fun initExtras() {
        val bundle = intent.extras
        if (bundle != null) {
            reposName = bundle.getString("repositoryName")
            ownerName = bundle.getString("owner")
        }

    }

    private fun initAdapters() {

        pullRequestAdapter = PullRequestAdapter(object : PullRequestAdapter.OnItemClickListener {
            override fun onItemClick(pullRequest: PullRequest) {
                itemClick(pullRequest)
            }

        })

        pullRequestViewModel = PullRequestViewModel(applicationContext)

        pullRequestViewModel.getLoadStatus().observe(this, Observer {
            loadFinished = it!!.finished
        })
        pullRequestViewModel.getPullRequests()
            .observe(this, Observer {
                prepareData(it)
            })


    }

    private fun prepareData(pulls: MutableList<PullRequest>?) {
        if (loadFinished) {
            if (pulls != null) {
                val total = pulls.count()
                if (total > 0) {
                    showData(pulls)
                } else {
                    showError()
                }

            } else {
                showError()
            }
            progressPullBar.visibility = View.GONE
        }
    }

    private fun showData(pulls: MutableList<PullRequest>) {
        bindig.opened = "${pulls.count()} pulls"
        pullRequestAdapter.updateRepositories(pulls)
        rvPullRequest.visibility = View.VISIBLE
        feedError.visibility = View.GONE
    }

    private fun showError() {
        bindig.opened = "0 pulls"
        rvPullRequest.visibility = View.GONE
        feedError.visibility = View.VISIBLE
    }

    private fun initView() {

        val actionbar = supportActionBar
        actionbar?.let {
            it.title = reposName
            it.setDisplayHomeAsUpEnabled(true)
        }
        btnBack.setOnClickListener { onBackPressed() }

        val linearVertical = LinearLayoutManager(this)
        linearVertical.orientation = LinearLayoutManager.VERTICAL
        rvPullRequest.layoutManager = linearVertical
        rvPullRequest.adapter = pullRequestAdapter
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            it.putParcelableArrayList(
                PULLSPARCEL,
                ArrayList(pullRequestAdapter.getPullRequests())
            )
            it.putParcelable(RVPOSITION, rvPullRequest.layoutManager!!.onSaveInstanceState())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val list = it.getParcelableArrayList<PullRequest>(PULLSPARCEL)
            showData(list)
            rvPullRequest.layoutManager!!.onRestoreInstanceState(it.getParcelable(RVPOSITION))
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun itemClick(pullRequest: PullRequest) {

    }
}
