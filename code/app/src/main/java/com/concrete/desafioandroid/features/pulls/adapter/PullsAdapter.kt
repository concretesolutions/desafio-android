package com.concrete.desafioandroid.features.pulls.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.loaders.loadImageCircle
import com.concrete.desafioandroid.data.models.PullRequest

class PullsAdapter(val pullsList: List<PullRequest>,
                   val context: Context,
                   val listener: (item: PullRequest) -> Unit): RecyclerView.Adapter<PullsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_pull, parent, false)

        return PullsViewHolder.newInstance(itemView, { position ->
            listener(pullsList[position])
        })
    }

    override fun getItemCount(): Int = pullsList.size

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        with(pullsList[position]) {
            holder.bindViews(this)

            holder.itemPullOwnerImageView.loadImageCircle(context, this.userInfo.avatarIcon)
        }
    }
}