package com.example.artul.util

import android.util.Log
import android.widget.AbsListView

abstract class EndlessScrollListener : AbsListView.OnScrollListener {

    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotal = 0
    private var load = false
    private var startPageIndex = 0

    constructor()

    constructor(visibleThreadshold: Int){

        this.visibleThreshold = visibleThreadshold

    }

    constructor(visibleThreadshold: Int, startPage: Int){

        this.visibleThreshold = visibleThreadshold
        this.startPageIndex = startPage
        this.currentPage = startPage

    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

        if(totalItemCount < previousTotal){

            this.currentPage = this.startPageIndex
            this.previousTotal = totalItemCount

            if(totalItemCount == 0) {

                this.load = true

            }

        }

        if(load && totalItemCount > previousTotal) {

            load = false
            previousTotal = totalItemCount
            currentPage++

        }

        if(!load && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {

            load = onLoadMore(currentPage + 1, totalItemCount)

        }

    }

    abstract fun onLoadMore(page: Int, totalItemCount: Int) : Boolean

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        Log.d("@@@@@@@@@@@@@@@@@", scrollState.toString())
    }

}