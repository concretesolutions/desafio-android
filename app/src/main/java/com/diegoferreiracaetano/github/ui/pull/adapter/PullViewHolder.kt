package com.diegoferreiracaetano.github.ui.pull.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.ItemPullBinding

class PullViewHolder(val binding: ItemPullBinding, val listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

    interface OnItemClickListener {
        fun onItemClick(view: View,pull: Pull)
    }

    fun bindTo(pull: Pull?) {
        binding.pull = pull
        if (pull != null) {
            itemView.setOnClickListener { listener.onItemClick(it,pull) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup,listener: OnItemClickListener): PullViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemPullBinding = DataBindingUtil.inflate(inflater, R.layout.item_pull, parent, false)
            return PullViewHolder(binding,listener)
        }
    }
}