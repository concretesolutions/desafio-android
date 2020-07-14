package com.bassul.mel.app.feature.pullRequestList.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.ext.AlertDialogUtils
import com.bassul.mel.app.feature.pullRequestList.view.adapter.PullRequestAdapter
import com.bassul.mel.app.feature.repositoriesList.PullRepositoryPresenterImpl
import com.bassul.mel.app.feature.repositoriesList.PullRequestListContract
import com.bassul.mel.app.feature.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.feature.repositoriesList.repository.PullRequestRepositoryImpl
import com.bassul.mel.app.feature.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.interactor.PullRequestInteractorImpl
import com.bassul.mel.app.interactor.RepoInteractorImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pull_request.*


class PullRequestActivity : AppCompatActivity(), PullRequestListContract.View{

    private var adapter: PullRequestAdapter? = null

    private val presenter : PullRequestListContract.Presenter by lazy {
        PullRepositoryPresenterImpl(this)
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
        aprProgressbar.visibility = View.GONE
    }

    val itemOnClick : (PullRequest) -> Unit = { item ->
        openUrlInBrowser(item?.html_url)
        arProgressbar?.visibility = View.VISIBLE
        arRecyclerViewRepositories?.visibility = View.GONE
    }

    fun openUrlInBrowser(url : String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    override fun onStop() {
        super.onStop()
        arProgressbar?.visibility = View.GONE
        arRecyclerViewRepositories?.visibility = View.VISIBLE
    }

    override fun showListPullRequest(pullRequest: ArrayList<PullRequest>) {
        initRecyclerView(pullRequest)
        showTextIfEmptyList(pullRequest)
    }

    override fun showErrorPullRequestCard(errorPullRequest: Int) {
        AlertDialogUtils.createAndShowAlertDialog(errorPullRequest, this)
    }

}
