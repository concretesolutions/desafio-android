package com.concrete.desafioandroid.features.repos.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.loaders.loadImageCircle
import com.concrete.desafioandroid.data.models.Repo

class ReposAdapter(private val reposList: List<Repo>,
                   private val context: Context,
                   private val listener: (item: Repo) -> Unit): RecyclerView.Adapter<ReposViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_repos, parent, false)

        return ReposViewHolder.newInstance(itemView, { position ->
            listener(reposList[position])
        })
    }

    override fun getItemCount(): Int = reposList.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        with(reposList[position]) {
            holder.bindViews(this)

            holder.itemOwnerImageView.loadImageCircle(context, this.ownerOverview.avatarIcon)
        }
    }
}