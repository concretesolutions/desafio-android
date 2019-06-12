package com.abs.javarepos.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abs.javarepos.R
import com.abs.javarepos.model.Repo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter(private val onItemClickListener: OnItemClickListener) :
    PagedListAdapter<Repo, RepoAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(repo: Repo) {
            itemView.apply {
                setOnClickListener { onItemClickListener.onItemClick(repo) }

                tvRank.text = adapterPosition.toString()
                tvName.text = repo.name
                tvDescription.text = repo.description
                tvAuthorName.text = repo.owner.login

                Glide.with(context)
                    .load(repo.owner.avatarUrl)
                    .into(ivAuthorPicture)

                tvStars.text = repo.stars.toString()
                tvForks.text = repo.forks.toString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repo: Repo)
    }
}