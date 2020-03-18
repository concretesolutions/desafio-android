package com.example.gitrepositories.modules.pull_requests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitrepositories.R
import com.example.gitrepositories.model.dto.PullRequest
import kotlinx.android.synthetic.main.pull_request_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class PullRequestViewHolder(view: View, private val context: Context, private val clickListener: (PullRequest) -> Unit): RecyclerView.ViewHolder(view) {

    fun bind(pullRequest: PullRequest?) {
        if (pullRequest == null) return

        itemView.title.text = pullRequest.title
        itemView.description.text = pullRequest.description
        itemView.username.text = pullRequest.user.username
        itemView.date.text = getDateString(pullRequest.date)

        Glide.with(context)
            .load(pullRequest.user.image)
            .placeholder(R.drawable.ic_user)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.picture)

        itemView.setOnClickListener { clickListener.invoke(pullRequest) }
    }

    private fun getDateString(date: String): String {
        val pattern = context.getString(R.string.date_format)
        return SimpleDateFormat(pattern, Locale.US).format(date)
    }

    companion object {
        fun create(parent: ViewGroup, context: Context, clickListener: (PullRequest) -> Unit): PullRequestViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.pull_request_item, parent, false)
            return PullRequestViewHolder(view, context, clickListener)
        }
    }
}