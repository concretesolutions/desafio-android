package com.uharris.desafio_android.presentation.sections.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uharris.desafio_android.R
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.utils.CircleTransform
import kotlinx.android.synthetic.main.item_repository.view.*

class MainAdapter(private var repositories: ArrayList<Repository>, private val listener: (Repository) -> Unit):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repositories[position], listener)

    fun setItems(repositoryList: List<Repository>) {
        repositories.addAll(repositoryList)
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<Repository> {
        return repositories
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Repository, listener: (Repository) -> Unit) = with(itemView) {
            nameTextView.text = item.owner.login
            titleTextView.text = item.name
            bodyTextView.text = item.description
            forkTextView.text = item.forks.toString()
            starsTextView.text = item.stars.toString()

            Picasso.get().load(item.owner.avatar).transform(CircleTransform()).into(userImageView)

            setOnClickListener { listener(item) }
        }
    }
}