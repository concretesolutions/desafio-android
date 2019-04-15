package com.dobler.desafio_android.ui.repository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.databinding.ListRvRepositoryBinding
import com.dobler.desafio_android.util.paging.NetworkState
import kotlinx.android.synthetic.main.list_rv_user.view.*

class RepositoryListAdapter(private val onClick: (GithubRepository) -> Unit) :
    PagedListAdapter<GithubRepository, RepositoryListAdapter.GithubRepositoryViewHolder>(diffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubRepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ListRvRepositoryBinding = ListRvRepositoryBinding.inflate(
            inflater,
            parent,
            false
        )

        return GithubRepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {

        getItem(position)?.let {
            holder.view.repository = it
            holder.view.executePendingBindings()

            val repository = it

            Glide.with(holder.itemView.context)
                .load(it.owner.avatar_url)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemView.ivUserImage)

            holder.itemView.setOnClickListener {
                onClick(repository)
            }
        }
    }

    class GithubRepositoryViewHolder(val view: ListRvRepositoryBinding) : RecyclerView.ViewHolder(view.root) {
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

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
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<GithubRepository>() {
            override fun areItemsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
                return oldItem == newItem
            }

        }
    }

}