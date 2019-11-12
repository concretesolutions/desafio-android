package com.ruiderson.desafio_android

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruiderson.desafio_android.api.ApiGithub
import com.ruiderson.desafio_android.api.RetrofitInitializer
import com.ruiderson.desafio_android.enum.IntentCode
import com.ruiderson.desafio_android.models.ParcelableBoolean
import com.ruiderson.desafio_android.models.PullRequest
import com.ruiderson.desafio_android.models.Repository
import com.ruiderson.desafio_android.ui.EndlessScroll
import com.ruiderson.desafio_android.ui.PullAdapter

import kotlinx.android.synthetic.main.activity_pull.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class PullActivity : AppCompatActivity() {


    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var endlessScroll: EndlessScroll
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PullAdapter
    private lateinit var fab: FloatingActionButton
    private var repository: Repository? = null
    private var apiService: ApiGithub? = null
    private var page: Int = 1
    private var openedCount = 0
    private var closedCount = 0

    private var recyclerViewState: Parcelable? = null
    private var recyclerViewItemsState: ArrayList<PullRequest>? = null
    private var fabState = ParcelableBoolean(false)
    private var pageLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull)
        setSupportActionBar(toolbar)


        //Back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        repository = (intent.getParcelableExtra(IntentCode.REPOSITORY_EXTRA.value) as Repository)

        repository?.let {
            supportActionBar?.title = repository?.name
        }


        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {

            page = 1
            loadRepository()

        }


        recyclerView = findViewById(R.id.pullRecyclerView)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = PullAdapter()
        recyclerView.adapter = adapter


        fab = findViewById(R.id.fab)
        fab.setOnClickListener{
            fab.hide()
            recyclerView.scrollToPosition(0)
        }


        endlessScroll = object : EndlessScroll(recyclerView, 60){
            override fun onFirstItem() {
                fab.hide()
            }
            override fun onScroll() {
                fab.show()
            }
            override fun onLoadMore() {

                page++
                loadRepository()

            }
        }


    }

    override fun onResume() {
        super.onResume()


        if(!pageLoaded){


            //Restore the RecyclerView states
            var stateRestored = false
            recyclerViewState?.let {
                recyclerViewItemsState?.let {

                    adapter.loadItems((recyclerViewItemsState as ArrayList<PullRequest>))
                    recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
                    stateRestored = true

                    val progressBar: ProgressBar = findViewById(R.id.progressBar)
                    if(progressBar.visibility == View.VISIBLE){
                        progressBar.visibility = View.INVISIBLE
                    }

                    if(fabState.value){
                        fab.show()
                    }


                }
            }


            //Loads page 1
            if(!stateRestored){
                loadRepository()
            }


            pageLoaded = true
        }
    }



    private fun loadRepository() {

        if(apiService == null) {
            apiService = RetrofitInitializer().githubService()
        }


        //API data
        var pullUser = ""
        repository?.owner?.let {
            pullUser = repository?.owner?.login.toString()
        }
        var repositoryName = ""
        repository?.let {
            repositoryName = repository?.name.toString()
        }


        //Load the current page
        val call = apiService?.getPulls(pullUser,repositoryName)
        call?.enqueue(object: Callback<ArrayList<PullRequest>?> {
            override fun onResponse(call: Call<ArrayList<PullRequest>?>?,
                                    response: Response<ArrayList<PullRequest>?>?) {
                if(response?.code() == 200) {
                    response.body()?.let {

                        val pulls: ArrayList<PullRequest> = it


                        //Check if current page is the number 1
                        if(page == 1) {

                            if(swipeRefresh.isRefreshing){
                                swipeRefresh.isRefreshing = false
                            }

                            val progressBar: ProgressBar = findViewById(R.id.progressBar)
                            if(progressBar.visibility == View.VISIBLE){
                                progressBar.visibility = View.INVISIBLE
                            }

                            adapter.loadItems(pulls)
                            openedCount = 0
                            closedCount = 0


                        } else {

                            adapter.addItems(pulls)

                        }


                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<PullRequest>?>?,
                                   t: Throwable?) {
            }
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        //Saves the RecyclerView`s state
        val viewState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable("STATE", viewState)
        outState.putParcelableArrayList("ITEMS", adapter.getItems())
        outState.putParcelable("FAB", ParcelableBoolean(fab.isVisible))

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            recyclerViewState = savedInstanceState.getParcelable("STATE")
            recyclerViewItemsState = savedInstanceState.getParcelableArrayList("ITEMS")
            fabState = savedInstanceState.getParcelable("FAB")
        }

    }
}