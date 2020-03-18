package com.example.gitrepositories.modules.repositories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitrepositories.R
import com.example.gitrepositories.model.dto.Repository
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryViewHolder(view: View, private val context: Context, private val clickListener: (Repository) -> Unit): RecyclerView.ViewHolder(view) {

    fun bind(repository: Repository?) {
        if (repository == null) return

        itemView.repository_title.text = repository.title
        itemView.repository_description.text = repository.description
        itemView.username.text = repository.username
        itemView.complete_name.text = repository.completeName
        itemView.star_count.text = repository.starCount.toString()
        itemView.fork_count.text = repository.forkCount.toString()

        Glide.with(context)
            .load(repository.image)
            .placeholder(R.drawable.ic_user)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.repository_picture)

        itemView.setOnClickListener { clickListener.invoke(repository) }
    }

    companion object {
        fun create(parent: ViewGroup, context: Context, clickListener: (Repository) -> Unit): RepositoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
            return RepositoryViewHolder(view, context, clickListener)
        }
    }
}