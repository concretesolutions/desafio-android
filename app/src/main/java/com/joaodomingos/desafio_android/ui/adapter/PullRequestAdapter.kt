package com.joaodomingos.desafio_android.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.PullRequestModel
import com.joaodomingos.desafio_android.ui.holder.ListFooterPullRequestViewHolder
import com.joaodomingos.desafio_android.ui.holder.PullRequestViewHolder

class PullRequestAdapter (private val retry: () -> Unit)
    : PagedListAdapter<PullRequestModel, RecyclerView.ViewHolder>(PullRequestAdapter.ItemDiffCallback) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) PullRequestViewHolder.create(parent) else ListFooterPullRequestViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as PullRequestViewHolder).bind(getItem(position))
        else (holder as ListFooterPullRequestViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    companion object {
        val ItemDiffCallback = object : DiffUtil.ItemCallback<PullRequestModel>() {
            override fun areItemsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}