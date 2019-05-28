package com.jmc.desafioandroidkotlin.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import com.jmc.desafioandroidkotlin.R
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem
import com.jmc.desafioandroidkotlin.presentation.ui.pagging.RepositoryDiffUtil
import com.jmc.desafioandroidkotlin.presentation.ui.viewholders.RepositoryViewHolder


open class PagedRepositoryAdapter(
    private val callback: AdapterCallback
) : PagedListAdapter<RepositoryItem, RepositoryViewHolder>(RepositoryDiffUtil()) {

    interface AdapterCallback {
        fun onRepositoryClicked(item: RepositoryItem)
        fun onUserClicked(item: RepositoryItem)

        fun loadImage(url: String, imageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)

        return RepositoryViewHolder(itemView, callback)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position) ?: return

        holder.bindView(item)
    }
}