package com.example.githubtest.features.pullrequest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubtest.R
import com.example.githubtest.data.model.PullRequest
import com.example.githubtest.data.model.ViewStateModel
import kotlinx.android.synthetic.main.activity_pull_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class PullRequestActivity : AppCompatActivity(), PullRequestClickListener {

    private var adapter : PullRequestAdapter = PullRequestAdapter(ArrayList(), this)
    private val pullRequestViewModel: PullRequestViewModel by viewModel()
    private lateinit var layoutManager: LinearLayoutManager
    val PULL_REQUEST_STATE_OPEN = "open"
    val PULL_REQUEST_STATE_CLOSED = "closed"

    private var repositoryName: String = String()
    private var repositoryOwnerName: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        intent.getStringExtra("repositoryName")?.let {nome ->
            repositoryName = nome
        }
        intent.getStringExtra("repositoryOwnerName")?.let {ownerName ->
            repositoryOwnerName = ownerName
        }

        supportActionBar?.title = repositoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initPullRequests()
        initObservable()
    }

    private fun initObservable() {
        getPullRequests()
        this.lifecycle.addObserver(pullRequestViewModel)
        pullRequestViewModel.getPullRequests().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.LOADING -> {
                   progressBar.visibility = View.VISIBLE
                }
                ViewStateModel.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    stateModel.model?.let { pullrequests ->
                        if (pullrequests.isEmpty()) showEmptyPullRequestMessage()
                        else {
                            adapter.addPullRequests(pullrequests)
                            val closedPr = pullrequests.count { it.state == PULL_REQUEST_STATE_CLOSED}
                            val openPr = pullrequests.count { it.state == PULL_REQUEST_STATE_OPEN }
                            status.text = getString(R.string.status, openPr, closedPr)
                        }
                    } ?: run{
                        showEmptyPullRequestMessage()
                    }
                }
                ViewStateModel.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    showNetworkError()
                }
            }
        })    }

    private fun initPullRequests() {
        layoutManager = LinearLayoutManager(this)
        rvPullRequest.layoutManager = layoutManager
        rvPullRequest.adapter = adapter
    }
    private fun getPullRequests() {
        pullRequestViewModel.loadPullRequests(repositoryOwnerName, repositoryName)
    }
    private fun showNetworkError() {
        Toast.makeText(this,"Verifique sua conexão com a internet",Toast.LENGTH_LONG).show()

    }
    private fun showEmptyPullRequestMessage() {
        Toast.makeText(this,"Não foram encontrados registros", Toast.LENGTH_LONG).show()

    }
    override fun onClick(pullRequest: PullRequest) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(pullRequest.html_url)
        startActivity(i)    }
}
