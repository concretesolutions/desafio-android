package com.bassul.mel.app.feature.pullRequestList.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassul.mel.app.*
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestList.view.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.pull_request_activity.*


class PullRequestActivity : AppCompatActivity(){

    private var adapter: PullRequestAdapter? = null
    lateinit var listPullRequest : ArrayList<PullRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)

         listPullRequest = intent.getSerializableExtra("pullRequest") as ArrayList<PullRequest>
        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerViewPullRequest.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(this, listPullRequest, itemClickListener = itemOnClick)
        recyclerViewPullRequest.adapter = adapter
        pull_request_progressbar.visibility = View.GONE
    }

    val itemOnClick : (PullRequest) -> Unit = { item ->
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(item.html_url)
        startActivity(openURL)
    }
}
