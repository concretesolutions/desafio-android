package com.haldny.githubapp.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScroll(private val layoutManager: LinearLayoutManager):
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dimenX: Int, dimenY: Int) {
        super.onScrolled(recyclerView, dimenX, dimenY)

        if (dimenY > 0) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()
                ) {
                    loadMoreItems()
                }
            }

            hideItems()
        }
    }

    abstract fun loadMoreItems()
    abstract fun getTotalPageCount(): Int
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
    abstract fun hideItems()
}