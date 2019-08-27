package com.joaodomingos.desafio_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaodomingos.desafio_android.R
import com.joaodomingos.desafio_android.models.PullRequestModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter(
    val items: ArrayList<PullRequestModel>,
    val context: Context
) : RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pull_request, parent, false))
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

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
    }
}