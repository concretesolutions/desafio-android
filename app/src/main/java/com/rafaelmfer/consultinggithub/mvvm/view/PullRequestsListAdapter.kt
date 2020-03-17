package com.rafaelmfer.consultinggithub.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.mvvm.model.pullrequests.GitPullRequestResponse
import de.hdodenhof.circleimageview.CircleImageView

class PullRequestsListAdapter(var pullRequestList: List<GitPullRequestResponse>, private val listener: OnClickListenerGitHub) :
    RecyclerView.Adapter<PullRequestsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pull_requests_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pullRequestList[position]

        holder.apply {
            tvNamePullRequest.text = pullRequest.title
            tvDescriptionPullRequest.text = pullRequest.body
            tvUserNameLoginPull.text = pullRequest.user.login
//            tvFullNamePull.text = "teste"

            Glide.with(itemView)
                .load(pullRequest.user.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .dontAnimate()
                .into(civUserPullRequest)

            itemView.setOnClickListener {
                listener.onClickOpenWeb(pullRequest.htmlUrl)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamePullRequest: TextView = itemView.findViewById(R.id.tvNamePullRequest)
        val tvDescriptionPullRequest: TextView = itemView.findViewById(R.id.tvDescriptionPullRequest)
        val tvUserNameLoginPull: TextView = itemView.findViewById(R.id.tvUserNameLoginPull)
        val tvFullNamePull: TextView = itemView.findViewById(R.id.tvFullNamePull)
        val civUserPullRequest: CircleImageView = itemView.findViewById(R.id.civUserPullRequest)
    }
}