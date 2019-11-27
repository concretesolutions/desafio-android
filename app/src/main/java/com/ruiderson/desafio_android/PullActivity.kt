package com.ruiderson.desafio_android

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
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
import kotlinx.coroutines.*

class PullActivity : AppCompatActivity() {


    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var endlessScroll: EndlessScroll
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PullAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var repository: Repository
    private lateinit var apiService: ApiGithub
    private var page: Int = 1

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
        supportActionBar?.title = repository.name


        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {

            page = 1
            loadPulls()

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


        endlessScroll = object : EndlessScroll(recyclerView){
            override fun onFirstItem() {
                fab.hide()
            }
            override fun onScroll() {
                fab.show()
            }
            override fun onLoadMore() {

                page++
                loadPulls()

            }
        }

        apiService = RetrofitInitializer().githubService()


    }

    override fun onResume() {
        super.onResume()

        if(!pageLoaded){


            //Restore the RecyclerView state after rotation
            var stateRestored = false
            if(recyclerViewState != null){
                recyclerViewState?.let {
                    recyclerViewItemsState?.let {
                        displayItems((recyclerViewItemsState as ArrayList<PullRequest>))
                        recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
                        stateRestored = true

                        if(fabState.value){
                            fab.show()
                        }
                    }
                }
            }

            if(!stateRestored){
                page = 1
                loadPulls()
            }

            pageLoaded= true
        }
    }


    private fun loadPulls() = CoroutineScope(Dispatchers.Default).launch {
        async {

            val pulls = getPullOnline()
            withContext(Dispatchers.Main) {
                displayItems(pulls)
            }

        }.await()
    }


    private suspend fun getPullOnline(): ArrayList<PullRequest> = coroutineScope {
        val job = async{

            var pulls = ArrayList<PullRequest>()


            //API data
            val pullUser = repository.owner.login
            val repositoryName = repository.name

            val call = apiService.getPulls(pullUser,repositoryName)
            val response = call.execute()
            if(response?.code() == 200) {
                response.body()?.let {
                    pulls = it
                }
            }

            pulls
        }
        job.await()
    }


    private fun displayItems(pulls: ArrayList<PullRequest>){

        if(page == 1) {
            adapter.loadItems(pulls)
        } else {
            adapter.addItems(pulls)
        }

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        if(progressBar.visibility == View.VISIBLE){
            progressBar.visibility = View.INVISIBLE
        }

        if(swipeRefresh.isRefreshing){
            swipeRefresh.isRefreshing = false
        }
        endlessScroll.reset()

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