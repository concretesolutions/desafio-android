package com.diegoferreiracaetano.github.ui.pull.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.ui.NetworkStatusViewHolder

class PullAdapter(private val retryCallback: () -> Unit,
                  private val listener: PullViewHolder.OnItemClickListener) :
         PagedListAdapter<Pull, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
            R.layout.item_pull -> PullViewHolder.create(parent,listener)
            R.layout.item_network_state -> NetworkStatusViewHolder.create(parent,retryCallback)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_pull -> (holder as PullViewHolder).bindTo(getItem(position))
            R.layout.item_network_state ->(holder as NetworkStatusViewHolder).bindTo(networkState)
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_pull

        }
    }

    fun setNetworkState(newNetworkState: NetworkState?) {

        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }


    }

    companion object {
        val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Pull>() {
            override fun areContentsTheSame(oldItem: Pull, newItem: Pull): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Pull, newItem: Pull): Boolean =
                    oldItem.id == newItem.id
        }
    }
}