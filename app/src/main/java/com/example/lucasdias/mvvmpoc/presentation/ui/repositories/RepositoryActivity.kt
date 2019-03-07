package com.example.lucasdias.mvvmpoc.presentation.ui.repositories

import android.arch.lifecycle.Observer
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.lucasdias.mvvmpoc.R
import com.example.lucasdias.mvvmpoc.domain.entity.Repository
import com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests.PullRequestActivity
import com.example.lucasdias.mvvmpoc.utils.NetworkReceiver
import com.example.lucasdias.mvvmpoc.utils.NetworkUtil
import com.example.lucasdias.mvvmpoc.utils.PreferenceUtil
import com.example.lucasdias.mvvmpoc.utils.RequestPermissionStatusEnum
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber





class RepositoryActivity : AppCompatActivity(), NetworkReceiver.NetworkStatusListener {

    private val viewModel: RepositoryViewModel by inject()
    private val adapter: RepositoryAdapter by inject{ parametersOf(this) }
    private val initialProgressBar by lazy { findViewById<ProgressBar>(R.id.repositoryActivity_pbInitialProgressBar) }
    private val refillProgressBar by lazy { findViewById<ProgressBar>(R.id.repositoryActivity_pbRefillProgressBar) }
    private val constraintLayout by lazy { findViewById<ConstraintLayout>(R.id.repositoryActivity_clParentContraintLayout) }
    private lateinit var networkReceiver: NetworkReceiver

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private var repositoryList = ArrayList<Repository>()
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount:Int = 0
    private var totalItemCount:Int = 0
    private var loading = false
    private var page = 1
    private var isConnected = true
    private var wasDisconnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        networkReceiver = NetworkReceiver(this)
        registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        isConnected = NetworkUtil.isNetworkConnected(this)
        initPageNumber()
        initActionBar()
        initObserverRepositoryList()
        initObvserverRequestPermissionStatus()
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    private fun initPageNumber(){
        if(PreferenceUtil.getPage(this) == 0){
            PreferenceUtil.setPage(page, this)
        }
        else{
            page = PreferenceUtil.getPage(this)
        }
    }

    private fun initObvserverRequestPermissionStatus(){
        viewModel.requestPermissionStatus?.observe(this, Observer {
            if (it!=null){
                if(it == RequestPermissionStatusEnum.FORBIDDEN){
                    Toast.makeText(this, getString(R.string.forbidden_request_toast), Toast.LENGTH_LONG).show()
                    refillProgressBar.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun initObserverRepositoryList() {
        viewModel.repositoryList?.observe(this, Observer {
            if (it != null) {
                repositoryList.clear()
                repositoryList.addAll(it)
                adapter.updateRepositoryList(repositoryList)
                if(PreferenceUtil.getPage(this)>1){
                    refillProgressBar.visibility = View.INVISIBLE
                }

                if (adapter.itemCount == 0) {
                    if(isConnected) {
                        viewModel.loadRepositoryPageFromApi(1)
                    }
                }
                else{
                    initialProgressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                loading = false
                PreferenceUtil.setPage(page, this)
                page++

            }
            Timber.i("Repository observer response:: $it")
        })
    }

    private fun initRecyclerView(){
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView = findViewById(R.id.repositoryActivity_rvRepositoryList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (!loading && isConnected)
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = true
                            loadMoreRepositories()
                        }
                }
            }
        })
    }

    private fun initActionBar(){
        val actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setIcon(R.drawable.git_hub_text_icon)
    }

    private fun loadMoreRepositories() {
        Timber.i("Call load more repositories function.")
        refillProgressBar.visibility = View.VISIBLE
        viewModel.loadRepositoryPageFromApi(PreferenceUtil.getPage(this))
    }

    fun callPullRequestActivity(repositoryFullName: String?, repositoryId: Long?, color: Int?, name: String?) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("repositoryFullName", repositoryFullName)
        intent.putExtra("repositoryId", repositoryId.toString())
        intent.putExtra("repositoryColor", color.toString())
        intent.putExtra("repositoryName", name)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right ,R.anim.slide_out_left)
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
                initialProgressBar.visibility = View.VISIBLE
                viewModel.loadRepositoryPageFromApi(1)
            }
        }
        else if(!isConnected){
            wasDisconnected = true
            initialProgressBar.visibility = View.INVISIBLE
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            snackbar.show()
        }
    }
}