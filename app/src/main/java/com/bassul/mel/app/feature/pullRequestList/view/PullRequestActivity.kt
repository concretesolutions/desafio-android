package com.bassul.mel.app.feature.pullRequestList.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestList.view.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.activity_pull_request.*


class PullRequestActivity : AppCompatActivity(){

    private var adapter: PullRequestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setSupportActionBar(findViewById(R.id.aprToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        var listPullRequest = intent.getSerializableExtra("pullRequest") as ArrayList<PullRequest>

        showTextIfEmptyList(listPullRequest)
        initRecyclerView(listPullRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun showTextIfEmptyList(listPullRequest: ArrayList<PullRequest>){
        if (listPullRequest.isEmpty()) {
            aprTvEmptyList.visibility = View.VISIBLE
        }
    }

    fun initRecyclerView(listPullRequest: ArrayList<PullRequest>) {
        aprRecyclerViewPullRequest.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(this, listPullRequest, itemClickListener = itemOnClick)
        aprRecyclerViewPullRequest.adapter = adapter
        aprProgressbar.visibility = View.GONE
    }

    val itemOnClick : (PullRequest) -> Unit = { item ->
        openUrlInBrowser(item.html_url)
    }

    fun openUrlInBrowser(url : String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}
