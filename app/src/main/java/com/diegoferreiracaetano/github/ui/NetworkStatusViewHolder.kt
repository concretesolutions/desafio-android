package com.diegoferreiracaetano.github.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.ItemNetworkStateBinding
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStatusViewHolder(val binding: ItemNetworkStateBinding,private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.retry_button.setOnClickListener { retryCallback() }
    }

    fun bindTo(networkState: NetworkState?) {
        if(networkState != null) {
            binding.networkState = networkState
            binding.retryButton.setOnClickListener {
                retryCallback()
            }
            binding.executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStatusViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemNetworkStateBinding = DataBindingUtil.inflate(inflater, R.layout.item_network_state, parent, false)
            return NetworkStatusViewHolder(binding, retryCallback)
        }
    }
}