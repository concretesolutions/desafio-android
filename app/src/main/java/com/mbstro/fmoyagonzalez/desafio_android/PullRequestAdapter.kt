package com.mbstro.fmoyagonzalez.desafio_android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.DateFormat


class PullRequestAdapter(private val items: ArrayList<PullRequest>, private val listener: (PullRequest) -> Unit) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_request, parent, false) as View)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title_pull_request)
        private val description: TextView = itemView.findViewById(R.id.body_pull_request)
        private val username: TextView = itemView.findViewById(R.id.name_user)
        private val avatar: ImageView = itemView.findViewById(R.id.avatar_user)
        private val date: TextView = itemView.findViewById(R.id.date_pull_request)

        fun bind(pullRequest: PullRequest, listener: (PullRequest) -> Unit) = with(itemView) {
            title.text = pullRequest.title
            description.text = pullRequest.body
            username.text = pullRequest.user.login
            Glide.with(itemView).load(pullRequest.user.avatar_url).into(avatar)
            date.text = DateFormat.getDateInstance().format(pullRequest.updated_at.time)
            setOnClickListener { listener(pullRequest) }
        }
    }
}