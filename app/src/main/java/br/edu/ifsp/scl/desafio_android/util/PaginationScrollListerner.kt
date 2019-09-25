package br.edu.ifsp.scl.desafio_android.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class PaginationScrollListener(var linearLayoutManager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var visibleItemCount: Int = linearLayoutManager.childCount
        var totalItemCount: Int = linearLayoutManager.itemCount
        var firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItens()
            }
        }
    }

    protected abstract fun loadMoreItens()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}