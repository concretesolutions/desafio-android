package com.example.challengecskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val PAGE_START = 1
    private val QUERY = "language:Java"
    private val SORT = "stars"
    private var isLoading = false
    private var isLastPage = false
    private val TOTAL_PAGES = 34
    private var currentPage = PAGE_START
    private var adapter: RepoAdapter = RepoAdapter(this)
    private lateinit var loadingBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        loadingBar = findViewById(R.id.main_progress)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun isLastPage() = isLastPage
            override fun isLoading() = isLoading
            override fun getTotalPageCount() = TOTAL_PAGES

            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1

                //delay pra chamada de API
                Handler().postDelayed({ loadNextPage() }, 1000)
            }
        })
        loadFirstPage()

    }

    private fun loadFirstPage() {
        //d(TAG, "loadFirstPage: $PAGE_START")

        GithubApi.searchService.fetchAllUsers(QUERY, SORT, PAGE_START.toString()).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {

                //Enviando os dados pro adapter
                val results = fetchResults(response)
                loadingBar.visibility = View.GONE
                adapter.addAll(results)

                if (currentPage <= TOTAL_PAGES)
                    adapter.addLoadingFooter()
                else
                    isLastPage = true
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                //d("onFailure", "fail:" + t.message)
                loadingBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchResults(response: Response<SearchResponse>): List<Repo> {
        val repositories = response.body()
        return repositories!!.getRepos()
    }

    private fun loadNextPage() {
        //d(TAG, "loadNextPage: $currentPage")

        GithubApi.searchService.fetchAllUsers(QUERY, SORT, currentPage.toString()).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                adapter.removeLoadingFooter()
                isLoading = false

                val results = fetchResults(response)
                adapter.addAll(results)

                if (currentPage != TOTAL_PAGES)
                    adapter.addLoadingFooter()
                else
                    isLastPage = true
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
