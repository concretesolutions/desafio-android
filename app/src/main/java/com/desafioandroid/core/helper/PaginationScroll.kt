package  com.desafioandroid.core.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScroll(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            if (isLoading()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                    loadMoreItems()
                }
            }
        }
        hideMoreItems()
    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean
    abstract fun hideMoreItems()
}