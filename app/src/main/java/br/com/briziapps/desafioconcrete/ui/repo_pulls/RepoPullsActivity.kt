package br.com.briziapps.desafioconcrete.ui.repo_pulls

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.briziapps.desafioconcrete.R
import br.com.briziapps.desafioconcrete.adapters.AdapterRecyclerRepoPulls
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects
import br.com.briziapps.desafioconcrete.mvp.Mvp
import kotlinx.android.synthetic.main.activity_repo_pulls.*
import kotlinx.android.synthetic.main.content_repo_pulls.*

class RepoPullsActivity : AppCompatActivity(), Mvp.ViewRepoPulls {

    private lateinit var rVRepoPulls:RecyclerView
    private var pageSelected = 1

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var lastVisibleItem: Int = 0
    private lateinit var adapterRecyclerRepoPulls: AdapterRecyclerRepoPulls
    private var repositoriePulls:MutableList<GitHubRepoPullObjects>? = null

    private lateinit var presenterRepoPulls: PresenterRepoPulls
    private lateinit var repoFullName: String
    private lateinit var repoName: String

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_pulls)

        initViews()

        presenterRepoPulls = PresenterRepoPulls(this)
        presenterRepoPulls.getRepoPullsOnApi(repoFullName, pageSelected.toString())

    }

    private fun initViews(){
        setSupportActionBar(toolbar)


        val bundle = intent.extras
        if ( bundle != null){
            repoName = bundle.getString("repositorieName","")
            repoFullName = bundle.getString("pullRequestsUrl","")
        }
        Log.d("RepoPullsActivity", "pullRequestsUrl = $repoFullName")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_repo_pulls_title)
        supportActionBar?.subtitle = repoName

        progressBar = pBLoadReposPulls

        repositoriePulls = ArrayList()

        rVRepoPulls = rVRepositoriePulls
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rVRepoPulls.layoutManager = linearLayoutManager
        rVRepoPulls.setHasFixedSize(true)
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

        adapterRecyclerRepoPulls = AdapterRecyclerRepoPulls(repositoriePulls as ArrayList<GitHubRepoPullObjects>, this::recyclerRepoPullclick)
        rVRepoPulls.adapter = adapterRecyclerRepoPulls

        setRecyclerViewScrollListener()
    }

    override fun updateRecyclerViewRepoPulls(repositoriePulls: List<GitHubRepoPullObjects>) {
        this.repositoriePulls?.addAll(repositoriePulls)
        adapterRecyclerRepoPulls.notifyDataSetChanged()
        hideProgressBar()
        if (repositoriePulls.isNotEmpty())
            rVRepoPulls.addOnScrollListener(scrollListener)
        else
            if (this.repositoriePulls?.isEmpty()!!)
                tVRepoPullsEmpty.visibility = View.VISIBLE
        Log.d("RepoPullsActivity", "repositoriePulls size = ${repositoriePulls.size}")

    }


    private fun recyclerRepoPullclick(pullRequestsUrl:String){
        val intentOpenPullOnBrowser = Intent(Intent.ACTION_VIEW)
        intentOpenPullOnBrowser.data = Uri.parse(pullRequestsUrl)
        startActivity(intentOpenPullOnBrowser)
    }

    private fun setRecyclerViewScrollListener(){

        scrollListener = object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                //Log.d("MainActivity", "totalItemCount = $totalItemCount lastVisibleItem = ${linearLayoutManager.findLastVisibleItemPosition()}")
                if (totalItemCount != null) {
                    if (totalItemCount > 6 && totalItemCount == linearLayoutManager.findLastVisibleItemPosition() + 1){
                        rVRepoPulls.removeOnScrollListener(scrollListener)
                        progressBar.visibility = View.VISIBLE
                        pageSelected ++
                        presenterRepoPulls.getRepoPullsOnApi(repoFullName, pageSelected.toString())
                        Log.d("RepoPullsActivity", "Load new list pageSelected = $pageSelected")
                    }
                }
            }
        }
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home ->{
            finish()
            overridePendingTransition(R.animator.slide_in_left, R.animator.slid_out_rigth)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.animator.slide_in_left, R.animator.slid_out_rigth)
    }
}
