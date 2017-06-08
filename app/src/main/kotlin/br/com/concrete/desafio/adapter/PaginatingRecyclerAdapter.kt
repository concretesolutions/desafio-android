package br.com.concrete.desafio.adapter

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.concrete.desafio.R
import br.com.concrete.sdk.model.Page
import kotlinx.android.synthetic.main.item_progress.view.*

open class PaginatingRecyclerAdapter<T : Parcelable> : BaseRecyclerAdapter<T>() {

    init {
        register<PaginatingRecyclerAdapter<T>>(R.layout.item_progress) {
            bind { _, _, view ->
                view.tag = PROGRESS_TAG
                view.progress.visibility = if (hasError) GONE else VISIBLE
                view.errorText.visibility = if (hasError) VISIBLE else GONE
                view.errorText.setOnClickListener({
                    hasError = false
                    notifyItemChanged(items.size)
                })
            }
            rule { position, items -> position == items.size }
        }
    }

    companion object {
        private const val PROGRESS_TAG = "PaginatingRecyclerAdapter.ProgressTag"
        private const val HAS_NEXT_KEY = "PaginatingRecyclerAdapter.HasNext"
        private const val HAS_ERROR_KEY = "PaginatingRecyclerAdapter.HasError"
        private const val NEXT_PAGE_KEY = "PaginatingRecyclerAdapter.NextPage"
        private const val TOTAL_KEY = "PaginatingRecyclerAdapter.Total"
    }

    private var loadMore: ((Int) -> Unit)? = null
    private var hasLoadingItem: Boolean = false
    private var hasError: Boolean = false
    private var total: Int = 0
    private var nextPage: Int? = null

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

    override fun saveInstanceState(): Bundle {
        val bundle = super.saveInstanceState()
        bundle.putBoolean(HAS_NEXT_KEY, hasLoadingItem)
        bundle.putBoolean(HAS_ERROR_KEY, hasError)
        bundle.putInt(NEXT_PAGE_KEY, nextPage ?: 0)
        bundle.putInt(TOTAL_KEY, total)
        return bundle
    }

    override fun restoreInstanceState(bundle: Bundle?) {
        super.restoreInstanceState(bundle)
        if (bundle == null) return
        hasLoadingItem = bundle.getBoolean(HAS_NEXT_KEY, false)
        hasError = bundle.getBoolean(HAS_ERROR_KEY, false)
        nextPage = bundle.getInt(NEXT_PAGE_KEY, 0)
        total = bundle.getInt(TOTAL_KEY, 0)
    }

    fun loadMore(loadMore: (Int) -> Unit): PaginatingRecyclerAdapter<T> {
        this.loadMore = loadMore
        return this
    }

    fun addPage(page: Page<T>) {
        if (nextPage?.equals(page.nextPage) ?: false) return
        nextPage = page.nextPage
        hasError = false
        total = page.totalCount.toInt()

        if (hasLoadingItem) removeLoadingItem()
        addAll(page.items)
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
