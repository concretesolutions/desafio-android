package com.pedrenrique.githubapp.core.platform

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EndlessRecyclerViewScrollListener(
    private val layoutManager: RecyclerView.LayoutManager,
    private var visibleThreshold: Int = DEFAULT_VISIBLE_THRESHOLD,
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {
    // The minimum amount of items to have below your current scroll position
    // before loading more.

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var loading = true

    companion object {
        private const val DEFAULT_VISIBLE_THRESHOLD = 3
    }

    init {
        val spanCount = (layoutManager as? GridLayoutManager)?.spanCount
            ?: (layoutManager as? StaggeredGridLayoutManager)?.spanCount ?: 1
        visibleThreshold *= spanCount
    }

    constructor(layoutManager: RecyclerView.LayoutManager, onLoadMore: () -> Unit) :
            this(layoutManager, DEFAULT_VISIBLE_THRESHOLD, onLoadMore)

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount

        val lastVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager ->
                layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager ->
                layoutManager.findLastVisibleItemPosition()
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    layoutManager.findLastVisibleItemPositions(null)
                // get maximum element within the list
                getLastVisibleItem(lastVisibleItemPositions)
            }
            else -> 0
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            onLoadMore()
            loading = true
        }
    }

    // Call this method whenever performing new searches
    fun resetState() {
        this.previousTotalItemCount = 0
        this.loading = true
    }
}
