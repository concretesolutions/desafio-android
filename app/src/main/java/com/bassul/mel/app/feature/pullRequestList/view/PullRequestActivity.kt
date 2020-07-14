package com.bassul.mel.app.feature.pullRequestList.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.ext.AlertDialogUtils
import com.bassul.mel.app.feature.pullRequestList.view.adapter.PullRequestAdapter
import com.bassul.mel.app.feature.pullRequestList.presenter.PullRequestPresenterImpl
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.repository.PullRequestRepositoryImpl
import com.bassul.mel.app.feature.pullRequestList.interactor.PullRequestInteractorImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pull_request.*


class PullRequestActivity : AppCompatActivity(), PullRequestListContract.View{

    private var adapter: PullRequestAdapter? = null

    private val presenter : PullRequestListContract.Presenter by lazy {
        PullRequestPresenterImpl(this)
    }

    private val repository : PullRequestListContract.Repository by lazy {
        PullRequestRepositoryImpl(GithubAPI())
    }

    private val interactor : PullRequestListContract.Interactor by lazy {
        PullRequestInteractorImpl(presenter, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setSupportActionBar(findViewById(R.id.aprToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var login = intent.getSerializableExtra("login") as String
        var nameRepository = intent.getSerializableExtra("nameRepository") as String

        interactor.getSelectedItem(login, nameRepository)
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
        setLoadingState(false)
    }

    val itemOnClick : (PullRequest) -> Unit = { item ->
        openUrlInBrowser(item?.html_url)
        setLoadingState(true)
    }

    fun openUrlInBrowser(url : String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    override fun onStop() {
        super.onStop()
        setLoadingState(false)
    }

    override fun showListPullRequest(pullRequest: ArrayList<PullRequest>) {
        initRecyclerView(pullRequest)
        showTextIfEmptyList(pullRequest)
    }

    override fun showErrorPullRequestCard(errorPullRequest: Int) {
        AlertDialogUtils.createAndShowAlertDialog(errorPullRequest, this)
    }

    override fun setLoadingState(isLoading : Boolean){
        if(isLoading){
            aprProgressbar?.visibility = View.VISIBLE
            aprRecyclerViewPullRequest?.visibility = View.GONE
        }else{
            aprProgressbar?.visibility = View.GONE
            aprRecyclerViewPullRequest?.visibility = View.VISIBLE
        }
    }
}
