package com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lucasdias.mvvmpoc.R
import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.TextView
import com.example.lucasdias.mvvmpoc.utils.NetworkReceiver
import com.example.lucasdias.mvvmpoc.utils.NetworkUtil

class PullRequestActivity : AppCompatActivity(), NetworkReceiver.NetworkStatusListener {

    private val viewModel: PullRequestViewModel by inject()
    private val adapter: PullRequestAdapter by inject{ parametersOf(this) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var networkReceiver: NetworkReceiver

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.pullRequestActivity_pbProgressBar) }
    private val constraintLayout by lazy { findViewById<ConstraintLayout>(R.id.pullRequestActivity_clParentContraintLayout) }
    private val repositoryNameLayout by lazy { findViewById<ConstraintLayout>(R.id.pullRequestActivity_cvRepositoryName) }
    private val repositoryNameText by lazy { findViewById<TextView>(R.id.pullRequest_tvRepositoryName) }

    private var pullRequestList = ArrayList<PullRequest>()
    private lateinit var repositoryFullName: String
    private lateinit var repositoryId: String
    private lateinit var repositoryColor: String
    private lateinit var repositoryName: String

    private var isConnected = true
    private var wasDisconnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        repositoryFullName = intent.getStringExtra("repositoryFullName")
        repositoryId = intent.getStringExtra("repositoryId")
        repositoryColor = intent.getStringExtra("repositoryColor")
        repositoryName = intent.getStringExtra("repositoryName")

        initRepositoryView(repositoryColor, repositoryName)

        networkReceiver = NetworkReceiver(this)
        registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        isConnected = NetworkUtil.isNetworkConnected(this)

        viewModel.loadRepositoryPageFromApi(repositoryFullName, repositoryId)
        initActionBar()
        initObserverPullRequestList()
        initRecyclerView()
    }



    private fun initRepositoryView(repositoryColor: String, repositoryName: String) {
        repositoryNameLayout.setBackgroundColor(ContextCompat.getColor(this, repositoryColor.toInt()))
        repositoryNameText.text = repositoryName
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    private fun initRecyclerView(){
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView = findViewById(R.id.pullRequestActivity_rvPullRequestList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
    private fun initObserverPullRequestList() {
        viewModel.pullRequestList(repositoryId)?.observe(this, Observer {
            if (it != null) {
                pullRequestList.addAll(it)
                adapter.updatePullRequestList(pullRequestList)
                Timber.i("PullRequest observer response: $pullRequestList")
                if(!it.isEmpty()) {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initActionBar(){
        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setIcon(R.drawable.git_hub_text_icon)
    }

    fun callPullRequestOnBrowser(url: String) {
        Timber.i("callPullRequestOnBrowser")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onNetworkStatusChanged(isConnected: Boolean) {

        val snackbar= Snackbar.make(constraintLayout,getString(R.string.disconnected_message) ,Snackbar.LENGTH_INDEFINITE)
        val snackbarView = snackbar.view
        val snackbarText = snackbarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            snackbarText.textAlignment = View.TEXT_ALIGNMENT_CENTER
        else
            snackbarText.gravity = Gravity.CENTER_HORIZONTAL

        if(isConnected && wasDisconnected){
            snackbar.duration = Snackbar.LENGTH_LONG
            snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGreen))
            snackbarText.text = getString(R.string.connected_message)
            snackbar.show()

            if (adapter.itemCount == 0){
                progressBar.visibility = View.VISIBLE
                viewModel.loadRepositoryPageFromApi(repositoryFullName, repositoryId)
            }
        }
        else if(!isConnected){
            wasDisconnected = true
            progressBar.visibility = View.INVISIBLE
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            snackbar.show()
        }
    }
}



