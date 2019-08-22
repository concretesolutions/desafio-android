package com.joaodomingos.desafio_android.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaodomingos.desafio_android.R
import com.joaodomingos.desafio_android.models.PullRequestModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pullRequestItem: PullRequestModel?) {
        if (pullRequestItem != null) {
            itemView.pull_request_txt_title.text = pullRequestItem.title
            itemView.pull_request_txt_description.text = pullRequestItem.body
            itemView.pull_request_txt_username .text = pullRequestItem.user.userName
            itemView.pull_request_created.text = pullRequestItem.createdAt
            itemView.pull_request_updated.text = pullRequestItem.updatedAt
            Picasso.get().load(pullRequestItem.user.avatarUrl).into(itemView.pull_request_img_avatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PullRequestViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_pull_request, parent, false)
            return PullRequestViewHolder(view)
        }
    }
}