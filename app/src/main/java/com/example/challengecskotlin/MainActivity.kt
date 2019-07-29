package com.example.challengecskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val PAGE_START = 3
    var isLoading = false
    private var isLastPage = false
    private val TOTAL_PAGES = 10
    private var currentPage = PAGE_START
    private var adapter: RepoAdapter = RepoAdapter()
    private lateinit var loadingBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingBar = findViewById<ProgressBar>(R.id.main_progress)
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = adapter

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                // mocking network delay for API call
                Handler().postDelayed(object: Runnable {
                    public override fun run() {
                        loadNextPage()
                    }
                }, 1000)
            }
        })
        loadFirstPage()

    }

    private fun loadFirstPage() {
        d(TAG, "loadFirstPage: $PAGE_START")

        GithubApi.searchService.fetchAllUsers("language:Java", "stars", PAGE_START.toString()).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                // Got data. Send it to adapter

                val results = fetchResults(response)
                loadingBar.visibility = View.GONE
                adapter.addAll(results)

                if (currentPage <= TOTAL_PAGES)
                    adapter.addLoadingFooter()
                else
                    isLastPage = true
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                // TODO: 08/11/16 handle failure
            }
        })
    }

    private fun fetchResults(response: Response<SearchResponse>): List<Repo> {
        val topRatedMovies = response.body()
        return topRatedMovies!!.getRepos()
    }

    private fun loadNextPage() {
        d(TAG, "loadNextPage: $currentPage")

        GithubApi.searchService.fetchAllUsers("language:Java", "stars", currentPage.toString()).enqueue(object : Callback<SearchResponse> {
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
                t.printStackTrace()
            }
        })
    }
}
