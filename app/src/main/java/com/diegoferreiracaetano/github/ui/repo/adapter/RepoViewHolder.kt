package com.diegoferreiracaetano.github.ui.repo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.databinding.ItemRepoBinding

class RepoViewHolder(val binding: ItemRepoBinding,val listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {

    interface OnItemClickListener {
        fun onItemClick(view: View,repo: Repo)
    }

    fun bindTo(repo: Repo?) {
        binding.repo = repo
        if (repo != null) {
            itemView.setOnClickListener { listener.onItemClick(it,repo) }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup,listener: OnItemClickListener): RepoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemRepoBinding = DataBindingUtil.inflate(inflater, R.layout.item_repo, parent, false)
            return RepoViewHolder(binding,listener)
        }
    }
}