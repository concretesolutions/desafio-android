package com.concrete.desafioandroid.features.repos.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.models.Repo
import com.concrete.desafioandroid.utils.EMPTY_STRING
import kotlinx.android.synthetic.main.item_repos.view.*


class ReposViewHolder private constructor(
        itemView: View, onItemClickListener: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var itemOwnerImageView: ImageView

    init {
        itemView.setOnClickListener {
            onItemClickListener(adapterPosition)
        }
        itemOwnerImageView = itemView.findViewById(R.id.itemOwnerImageView)
    }

    companion object {
        fun newInstance(itemView: View, listener: (position: Int) -> Unit): ReposViewHolder {
            return ReposViewHolder(itemView, listener)
        }
    }

    fun bindViews(repo: Repo) {
        with(itemView) {
            itemRepoNameTextView.text = repo.name
            itemRepoDescriptionTextView.text = repo.description
            itemRepoForksTextView.text = repo.forksCount.toString()
            itemRepoStarsTextView.text = repo.starsCount.toString()

            itemOwnerLoginTextView.text = repo.ownerDetails?.login ?: repo.ownerOverview.login
            itemOwnerNameTextView.text = repo.ownerDetails?.name ?: EMPTY_STRING

        }
    }

}