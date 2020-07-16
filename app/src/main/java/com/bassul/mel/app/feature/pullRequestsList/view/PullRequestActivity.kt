package com.bassul.mel.app.feature.pullRequestsList.view

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
import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestsList.interactor.PullRequestInteractorImpl
import com.bassul.mel.app.feature.pullRequestsList.presenter.PullRequestPresenterImpl
import com.bassul.mel.app.feature.pullRequestsList.repository.PullRequestRepositoryImpl
import com.bassul.mel.app.feature.pullRequestsList.view.adapter.PullRequestAdapter
import kotlinx.android.synthetic.main.activity_pull_request.*


class PullRequestActivity : AppCompatActivity(), PullRequestListContract.View {

    private var adapter: PullRequestAdapter? = null

    private val presenter: PullRequestListContract.Presenter by lazy {
        PullRequestPresenterImpl(this)
    }

    private val repository: PullRequestListContract.Repository by lazy {
        PullRequestRepositoryImpl(GithubAPI())
    }

    private val interactor: PullRequestListContract.Interactor by lazy {
        PullRequestInteractorImpl(presenter, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        setSupportActionBar(findViewById(R.id.aprToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        initLoadingPullRequestData()
    }

    private fun initLoadingPullRequestData() {
        showLoading()

        val login = intent.getSerializableExtra("login") as String
        val nameRepository = intent.getSerializableExtra("nameRepository") as String

        interactor.getSelectedItem(login, nameRepository)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initRecyclerView(listPullRequest: ArrayList<PullRequest>) {
        aprRecyclerViewPullRequest.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(this, listPullRequest, itemClickListener = itemOnClick)
        aprRecyclerViewPullRequest.adapter = adapter
        hideLoading()
    }

    private val itemOnClick: (PullRequest) -> Unit = { item ->
        openUrlInBrowser(item.html_url)
        showLoading()
    }

    private fun openUrlInBrowser(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }

    override fun showPullRequestList(pullRequest: ArrayList<PullRequest>) {
        initRecyclerView(pullRequest)
        hideLoading()
    }

    override fun showTextEmptyList() {
        aprTvEmptyList.visibility = View.VISIBLE
        hideLoading()
    }

    override fun showErrorPullRequestList(errorPullRequest: Int) {
        AlertDialogUtils.createAndShowAlertDialog(errorPullRequest, this)
    }

    private fun hideLoading() {
        aprProgressbar?.visibility = View.GONE
        aprRecyclerViewPullRequest?.visibility = View.VISIBLE
    }

    private fun showLoading() {
        aprProgressbar?.visibility = View.VISIBLE
        aprRecyclerViewPullRequest?.visibility = View.GONE
    }
}

