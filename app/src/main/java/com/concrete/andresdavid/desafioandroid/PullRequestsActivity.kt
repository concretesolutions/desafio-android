package com.concrete.andresdavid.desafioandroid

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.concrete.andresdavid.desafioandroid.adapters.PullRequestAdapter
import com.concrete.andresdavid.desafioandroid.model.PullRequest
import com.concrete.andresdavid.desafioandroid.model.Resource
import com.concrete.andresdavid.desafioandroid.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_pull_requests.*
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.concrete.andresdavid.desafioandroid.util.Constants


class PullRequestsActivity : AppCompatActivity() {
    private val viewModel: PullRequestViewModel by lazy {
        ViewModelProviders.of(this).get(PullRequestViewModel::class.java)
    }
    private var isLoading = true
    private var repoFullName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        // set title bar name
        title = intent.getStringExtra(MainActivity.REPOSITORY_NAME_KEY)

        // set repo full name
        repoFullName = intent.getStringExtra(MainActivity.REPOSITORY_FULL_NAME_KEY)

        // init layout manager
        rv_list_pullrequest.layoutManager = LinearLayoutManager(this)

        // set adapter
        val pullRequestAdapter = PullRequestAdapter(mutableListOf(), this)
        rv_list_pullrequest.adapter = pullRequestAdapter

        // add default loading item
        pullRequestAdapter.pushItem(PullRequest("LOADING"))

        // add subscription to view model
        viewModel.getPullRequests(repoFullName).observe(this, Observer { result ->
            if (pullRequestAdapter.itemCount > 0) {
                pullRequestAdapter.popItem()
            }
            if (result?.status == Resource.SUCCESS) {
                tv_pr_data.text = result.data?.count().toString() + " Open Pull Request"
                if (result?.data?.isNotEmpty()!!) {
                    pullRequestAdapter.pushItems(result.data)
                }else{
                    pullRequestAdapter.pushItem(PullRequest("EMPTY"))
                }

            }else{
                val toast = Toast.makeText(this, result?.message, Toast.LENGTH_SHORT)
                toast.show()
            }
            isLoading = false
        })

    }
}
