package com.joaodomingos.desafio_android.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaodomingos.desafio_android.R
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.PullRequestModel
import com.joaodomingos.desafio_android.ui.adapter.PullRequestAdapter
import com.joaodomingos.desafio_android.ui.view_models.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PullRequestActivity : AppCompatActivity() {

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var adapter: PullRequestAdapter

    private lateinit var ownerName: String
    private lateinit var repositoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        ownerName = intent.getStringExtra("ownerName")
        repositoryName = intent.getStringExtra("repositoryName")

        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel::class.java)

        viewModel.loadPullRequests(ownerName, repositoryName)


        viewModel.pullRequestMutableLiveData.observe(this, Observer {
            if (it == null) {
                State.ERROR
            } else if (!::adapter.isInitialized) {
                setRecycleListView(it as ArrayList<PullRequestModel>)
            }
        })

    }

    private fun setRecycleListView(pullRequestList: ArrayList<PullRequestModel>) {
        adapter = PullRequestAdapter(pullRequestList, this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

}
