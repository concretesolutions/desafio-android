package com.ruiderson.desafio_android

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruiderson.desafio_android.api.ApiGithub
import com.ruiderson.desafio_android.api.RetrofitInitializer
import com.ruiderson.desafio_android.database.RepositoryDao
import com.ruiderson.desafio_android.database.RepositoryDatabase
import com.ruiderson.desafio_android.models.ParcelableBoolean
import com.ruiderson.desafio_android.models.Repository
import com.ruiderson.desafio_android.models.RepositoryBody
import com.ruiderson.desafio_android.models.RepositoryCache
import com.ruiderson.desafio_android.ui.EndlessScroll
import com.ruiderson.desafio_android.ui.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {


    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var endlessScroll: EndlessScroll
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoryAdapter
    private lateinit var fab: FloatingActionButton
    private var apiService: ApiGithub? = null
    private lateinit var tbRepository: RepositoryDao
    private var page: Int = 1

    private var recyclerViewState: Parcelable? = null
    private var recyclerViewItemsState: ArrayList<Repository>? = null
    private var fabState = ParcelableBoolean(false)
    private var pageLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        //Loads the DrawerLayout (blank)
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0,0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            page = 1
            loadRepository()
        }


        recyclerView = findViewById(R.id.repositoryRecyclerView)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = RepositoryAdapter()
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
                loadRepository()
            }
        }


        val database = Room.databaseBuilder(
            this,
            RepositoryDatabase::class.java,
            "Repository")
            .build()
        tbRepository = database.repositoryDao()

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
                        displayItems((recyclerViewItemsState as ArrayList<Repository>))
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
                loadRepository()
            }

            pageLoaded= true
        }
    }


    private fun loadRepository() = CoroutineScope(Dispatchers.Default).launch {
        async {

            var repositories: ArrayList<Repository>
            if(page == 1){
                repositories = getRepositoryOnCache()

                //If empty list, load online
                if(repositories.isEmpty()){
                    repositories = getRepositoryOnline()
                }else{

                    //Wait a few miliseconds, and loads the repository online
                    GlobalScope.launch{
                        delay(1500)
                        repositories = getRepositoryOnline()
                        withContext(Dispatchers.Main) {
                            displayItems(repositories)
                        }
                    }

                }
            } else {

                //Load page
                repositories = getRepositoryOnline()

            }
            withContext(Dispatchers.Main) {
                displayItems(repositories)
            }

        }.await()
    }


    private suspend fun getRepositoryOnCache(): ArrayList<Repository> = coroutineScope {
        val job = async{

            val repositories = ArrayList<Repository>()
            val listCache = tbRepository.getAll()
            if(listCache.isNotEmpty()){
                for(cache in listCache){
                    repositories.add(cache.getRepository())
                }
            }
            repositories

        }
        job.await()
    }


    private suspend fun getRepositoryOnline(): ArrayList<Repository> = coroutineScope {
        val job = async{

            var repositories = ArrayList<Repository>()

            val call = apiService?.getRepository("language:Java","stars", page)
            val response = call?.execute()
            if(response?.code() == 200) {
                response.body()?.let {
                    repositories = it.items

                    //Saves the cache
                    if(page == 1) {
                        if(repositories.isNotEmpty()){
                            saveCache(repositories)
                        }
                    }

                }
            }

            repositories

        }
        job.await()
    }


    private fun displayItems(repositories: ArrayList<Repository>){

        if(page == 1) {
            adapter.loadItems(repositories)
        } else {
            adapter.addItems(repositories)
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


    private fun saveCache(repositories: ArrayList<Repository>) = CoroutineScope(Dispatchers.IO).async {
        tbRepository.deleteAll()
        var order: Long = 0
        for(repository in repositories){
            order++
            tbRepository.insert(RepositoryCache(repository, order))
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        //Saves the RecyclerView's state
        val viewState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable("STATE", viewState)
        outState.putParcelableArrayList("ITEMS", adapter.getItems())
        outState.putParcelable("FAB", ParcelableBoolean(fab.isVisible))

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)


        //Restores the RecyclerView's state
        savedInstanceState?.let {
            recyclerViewState = savedInstanceState.getParcelable("STATE")
            recyclerViewItemsState = savedInstanceState.getParcelableArrayList("ITEMS")
            fabState = savedInstanceState.getParcelable("FAB")
        }

    }
}