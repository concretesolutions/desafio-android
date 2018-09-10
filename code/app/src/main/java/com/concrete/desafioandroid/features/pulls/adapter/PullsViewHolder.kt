package com.concrete.desafioandroid.features.pulls.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.models.PullRequest
import kotlinx.android.synthetic.main.item_pull.view.*


class PullsViewHolder private constructor(
        itemView: View, onItemClickListener: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var itemPullOwnerImageView: ImageView

    init {
        itemView.setOnClickListener {
            onItemClickListener(adapterPosition)
        }
        itemPullOwnerImageView = itemView.findViewById(R.id.itemPullOwnerImageView)
    }

    companion object {
        fun newInstance(itemView: View, listener: (position: Int) -> Unit): PullsViewHolder {
            return PullsViewHolder(itemView, listener)
        }
    }

    fun bindViews(pullRequest: PullRequest) {
        with(itemView) {
            itemPullNameTextView.text = pullRequest.title
            itemPullBodyTextView.text = pullRequest.body

            itemPullOwnerLoginTextView.text = pullRequest.userInfo.login ?: "Login"
//            itemOwnerLoginTextView.text = pullRequest.ownerDetails?.login ?: "Login"
//            itemOwnerNameTextView.text = pullRequest.ownerDetails?.name ?: "User name"

        }
    }

}