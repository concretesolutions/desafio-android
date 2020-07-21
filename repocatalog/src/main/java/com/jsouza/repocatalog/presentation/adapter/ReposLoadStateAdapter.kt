package com.jsouza.repocatalog.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.repocatalog.R
import com.jsouza.repocatalog.databinding.ReposLoadContainerItemBinding

class ReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ReposLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_load_container_item,
                parent,
                false)
        val binding = ReposLoadContainerItemBinding.bind(view)
        return LoadStateViewHolder(binding, retry)
    }

    inner class LoadStateViewHolder(
        private val binding: ReposLoadContainerItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButtonListItem.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(
            loadState: LoadState
        ) {
            if (loadState is LoadState.Error) {
                binding.errorMsgListItem.text = loadState.error.localizedMessage
            }
            binding.progressBarListItem.isVisible = loadState is LoadState.Loading
            binding.retryButtonListItem.isVisible = loadState !is LoadState.Loading
            binding.errorMsgListItem.isVisible = loadState !is LoadState.Loading
        }
    }
}
