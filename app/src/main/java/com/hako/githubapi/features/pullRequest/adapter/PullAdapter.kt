package com.hako.githubapi.features.pullRequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hako.githubapi.R
import com.hako.githubapi.domain.entities.PullRequest

class PullAdapter(val onClick: (PullRequest) -> Unit) : ListAdapter<PullRequest, PullItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullItemViewHolder = PullItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.pull_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: PullItemViewHolder, position: Int) {
        holder.pull = getItem(position)
        holder.view.setOnClickListener { onClick(getItem(position)) }
    }
}

class DiffCallback : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
        return oldItem.id == newItem.id
    }
}
