package com.bassul.mel.app.repositoriesList.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.*
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.repositoriesList.view.adapter.PullRequestAdapter
import com.bassul.mel.app.repositoriesList.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pull_request_activity.*
import java.io.Serializable


class PullRequestActivity : AppCompatActivity(){

    private var adapter: PullRequestAdapter? = null
    lateinit var listPullRequest : ArrayList<PullRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_request_activity)

         listPullRequest = intent.getSerializableExtra("pullRequest") as ArrayList<PullRequest>
        Log.i("listPullRequest", "listPullRequest   "+listPullRequest)



        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerViewPullRequest.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(this, listPullRequest, itemClickListener = itemOnClick)
        recyclerViewPullRequest.adapter = adapter

    }

    val itemOnClick : (Item) -> Unit = { item ->
        Log.i("desafio_android", "clicou pull request")
    }
}
