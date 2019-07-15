package com.pedrenrique.githubapp.features.common

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedrenrique.githubapp.core.ext.defaultFriendlyMsg
import com.pedrenrique.githubapp.core.platform.Adapter
import com.pedrenrique.githubapp.core.platform.EndlessRecyclerViewScrollListener
import com.pedrenrique.githubapp.features.common.model.ErrorItemModelHolder

abstract class BaseListFragment : Fragment() {

    private val RecyclerView.linearLayoutManager: LinearLayoutManager
        get() = layoutManager as LinearLayoutManager
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener


    protected fun RecyclerView.setup(adapter: Adapter, loadMore: () -> Unit) {
        layoutManager = LinearLayoutManager(context)
        endlessRecyclerViewScrollListener =
            EndlessRecyclerViewScrollListener(linearLayoutManager, loadMore)

        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        addItemDecoration(decoration)

        this.adapter = adapter
        setHasFixedSize(true)
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    protected fun <E> List<E>.showErrorIfNeeded() {
        val error =
            (lastOrNull { it is ErrorItemModelHolder } as? ErrorItemModelHolder)?.error?.error
        error?.run {
            Toast.makeText(context, defaultFriendlyMsg, Toast.LENGTH_LONG).show()
        }
    }
}