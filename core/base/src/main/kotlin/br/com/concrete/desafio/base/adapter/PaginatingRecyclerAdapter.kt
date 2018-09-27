package br.com.concrete.desafio.base.adapter

import android.content.Context
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.arch.toolkit.recycler.adapter.BaseRecyclerAdapter
import br.com.arch.toolkit.recycler.adapter.ViewBinder
import br.com.concrete.desafio.data.model.Page

private const val PROGRESS_TAG = "PaginatingRecyclerAdapter.ProgressTag"
private const val TYPE_PROGRESS = 999

open class PaginatingRecyclerAdapter<T : Parcelable> : BaseRecyclerAdapter<T>() {

    private var loadMore: ((Int) -> Unit)? = null
    private var hasLoadingItem: Boolean = false
    private var hasError: Boolean = false
    private var total: Int = 0
    private var nextPage: Int? = null

    override fun viewCreator(context: Context, viewType: Int): ViewBinder<*> {
        return when (viewType) {
            TYPE_PROGRESS -> object : ViewBinder<Any> {
                override fun bind(model: Any) {}
            }
            else -> object : ViewBinder<Any> {
                override fun bind(model: Any) {}
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount -> TYPE_PROGRESS
            else -> super.getItemViewType(position)
        }
    }

    override fun getItemCount() = super.getItemCount() + (if (hasLoadingItem) 1 else 0)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.clearOnChildAttachStateChangeListeners()
        recyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            private var lastRequestedPage = 0
            override fun onChildViewDetachedFromWindow(child: View) {}

            override fun onChildViewAttachedToWindow(child: View) {
                if (hasError) lastRequestedPage = 0
                if (lastRequestedPage == nextPage) return
                if (PROGRESS_TAG != child.tag || hasError) return
                lastRequestedPage = nextPage!!
                loadMore?.invoke(lastRequestedPage)
            }
        })
    }

    fun loadMore(loadMore: (Int) -> Unit): PaginatingRecyclerAdapter<T> {
        this.loadMore = loadMore
        return this
    }

    fun addPage(page: Page<T>) {
        if (nextPage?.equals(page.nextPage) == true) return
        nextPage = page.nextPage
        hasError = false
        total = page.totalCount.toInt()

        if (hasLoadingItem) removeLoadingItem()

        page.items.forEach(::addItem)
        if (!hasLoadingItem && nextPage != null) insertLoadingItem()
    }

    fun failPage() {
        if (!hasLoadingItem) return
        hasError = true
        notifyItemChanged(itemCount - 1)
    }

    private fun removeLoadingItem() {
        if (!hasLoadingItem) return
        hasLoadingItem = false
        notifyItemRemoved(itemCount)
    }

    private fun insertLoadingItem() {
        if (hasLoadingItem) return
        hasLoadingItem = true
        notifyItemInserted(itemCount - 1)
    }

}
