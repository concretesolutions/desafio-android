package com.gdavidpb.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.gdavidpb.github.R
import com.gdavidpb.github.presentation.model.PullItem
import com.gdavidpb.github.ui.viewholders.BaseViewHolder
import com.gdavidpb.github.ui.viewholders.PullViewHolder

open class PullAdapter(
    private val callback: AdapterCallback
) : BaseAdapter<PullItem>() {

    interface AdapterCallback {
        fun onPullClicked(item: PullItem)
        fun onUserClicked(item: PullItem)

        fun loadImage(url: String, imageView: ImageView)
    }

    override fun provideComparator() = compareBy(PullItem::id)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PullItem> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false)

        return PullViewHolder(itemView, callback)
    }
}