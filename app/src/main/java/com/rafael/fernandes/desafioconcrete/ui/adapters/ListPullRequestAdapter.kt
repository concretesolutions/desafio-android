package com.rafael.fernandes.desafioconcrete.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafael.fernandes.desafioconcrete.R
import com.rafael.fernandes.desafioconcrete.databinding.ListPullRequestItemBinding
import com.rafael.fernandes.desafioconcrete.databinding.ListRepositoryItemBinding
import com.rafael.fernandes.domain.model.GitPullRequests
import com.rafael.fernandes.domain.model.Item

class ListPullRequestAdapter constructor(private val itemClick: (GitPullRequests) -> Unit) :
    ListAdapter<GitPullRequests, ListPullRequestViewHolder>(ScheduleDiffCallback()) {

    private var listItem: ArrayList<GitPullRequests> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPullRequestViewHolder =
        ListPullRequestViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_pull_request_item, parent, false)
        )

    override fun onBindViewHolder(holder: ListPullRequestViewHolder, position: Int) {
        val binding = holder.binding
        binding?.pullRequest = getItem(position)
        binding?.executePendingBindings()
        binding?.root?.setOnClickListener {
            itemClick.invoke(getItem(position))
        }
    }

    fun updateList(list: List<GitPullRequests>?) {
        if (list != null) {
            listItem.addAll(list)
        }

        notifyDataSetChanged()
    }

    fun getList(): ArrayList<GitPullRequests> {
        return listItem
    }

    override fun submitList(list: MutableList<GitPullRequests>?) {
        if (list != null) {
            listItem.addAll(list)
        }
        super.submitList(listItem)
    }

    fun resetDataList() {
        getList().clear()
        notifyDataSetChanged()
    }

    private class ScheduleDiffCallback : DiffUtil.ItemCallback<GitPullRequests>() {
        override fun areItemsTheSame(oldItem: GitPullRequests, newItem: GitPullRequests): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: GitPullRequests, newItem: GitPullRequests): Boolean =
            oldItem == newItem
    }
}

class ListPullRequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ListPullRequestItemBinding? = DataBindingUtil.bind(view)
}