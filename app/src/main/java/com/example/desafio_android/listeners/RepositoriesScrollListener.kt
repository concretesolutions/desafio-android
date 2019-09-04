package com.example.desafio_android.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RepositoriesScrollListener(private val mLinearLayoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    private var currentPage = 1
    private var previousTotalItemCount = 0
    private var loading = true
    var firstVisibleItem: Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager!!.itemCount
        firstVisibleItem = mLinearLayoutManager!!.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            // Do something
            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    abstract fun onLoadMore(page: Int)
}