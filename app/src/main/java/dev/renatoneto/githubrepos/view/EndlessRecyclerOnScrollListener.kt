package dev.renatoneto.githubrepos.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private var previousTotal = 0
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = (recyclerView.layoutManager as? LinearLayoutManager) ?: return

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false
                previousTotal = totalItemCount
            }
        }

        val visibleThreshold = VISIBLE_THRESHOLD
        if (!isLoading &&
            (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold)) {
            isLoading = true
            onLoadMore()
        }
    }

    abstract fun onLoadMore()

    fun reset() {
        isLoading = true
        previousTotal = 0
    }
}