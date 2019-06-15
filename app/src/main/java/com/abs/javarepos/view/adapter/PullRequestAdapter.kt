package com.abs.javarepos.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abs.javarepos.R
import com.abs.javarepos.model.PullRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter(private val onItemClickListener: OnItemClickListener) :
    PagedListAdapter<PullRequest, PullRequestAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PullRequest>() {
            override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {
            itemView.apply {
                setOnClickListener { onItemClickListener.onItemClick(pullRequest) }

                tvTitle.text = pullRequest.title
                tvBody.text = pullRequest.body
                tvOwnerName.text = pullRequest.owner.login

                Glide.with(context)
                    .load(pullRequest.owner.avatarUrl)
                    .into(ivOwnerPicture)

                tvDate.text = pullRequest.date.toString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pullRequest: PullRequest)
    }
}