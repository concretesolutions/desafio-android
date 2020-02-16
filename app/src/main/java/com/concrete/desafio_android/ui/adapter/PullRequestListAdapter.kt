package com.concrete.desafio_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.desafio_android.R
import com.concrete.desafio_android.domain.PullRequest
import com.concrete.desafio_android.extension.formatString
import com.concrete.desafio_android.util.AVATAR_ICON_SIZE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_pull_request.view.textview_pull_request_date
import kotlinx.android.synthetic.main.list_item_pull_request.view.textview_pull_request_title
import kotlinx.android.synthetic.main.list_item_pull_request.view.textview_pull_request_body
import kotlinx.android.synthetic.main.list_item_pull_request.view.textview_pull_request_author_username
import kotlinx.android.synthetic.main.list_item_pull_request.view.pull_request_author_avatar

class PullRequestListAdapter(
    private val pullRequests: ArrayList<PullRequest>,
    private val context: Context,
    private val listener: (PullRequest) -> Unit
) : RecyclerView.Adapter<PullRequestListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pullRequest: PullRequest, listener: (PullRequest) -> Unit) = with(itemView) {
            textview_pull_request_body.text = pullRequest.body
            textview_pull_request_title.text = pullRequest.title
            textview_pull_request_author_username.text = pullRequest.user.login
            textview_pull_request_date.text = pullRequest.created_at.formatString()
            Picasso.get()
                .load(pullRequest.user.avatar_url)
                .resize(AVATAR_ICON_SIZE, AVATAR_ICON_SIZE)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.pull_request_author_avatar)
            setOnClickListener { listener(pullRequest) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_pull_request,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pullRequests[position], listener)
    }
}
