package com.gdavidpb.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import com.gdavidpb.github.R
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.ui.pagging.RepositoryDiffUtil
import com.gdavidpb.github.ui.viewholders.RepositoryViewHolder

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