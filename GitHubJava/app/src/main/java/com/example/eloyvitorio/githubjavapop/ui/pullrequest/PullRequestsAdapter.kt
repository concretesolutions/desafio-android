package com.example.eloyvitorio.githubjavapop.ui.pullrequest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.eloyvitorio.githubjavapop.R
import com.example.eloyvitorio.githubjavapop.data.model.PullRequest

class PullRequestsAdapter(private val clickItem: (String) -> Unit) :
        RecyclerView.Adapter<PullRequestsAdapter.ViewHolder>() {
    private var listOfPullRequests = mutableListOf<PullRequest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pullrequest_list_item,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfPullRequests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = listOfPullRequests[position]
        holder.bindView(pullRequest, clickItem)
    }

    fun addAll(newListPullRequest: List<PullRequest>) {
        if (listOfPullRequests.isNullOrEmpty()) {
            listOfPullRequests.addAll(newListPullRequest)
            notifyDataSetChanged()
        } else {
            val curSize = itemCount
            listOfPullRequests.addAll(newListPullRequest)
            notifyItemRangeInserted(curSize, listOfPullRequests.size - 1)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pullRequest: PullRequest, onClick: (String) -> Unit) {
            val pullRequestTitle = itemView.findViewById<TextView>(R.id.textViewPrTitle)
            val pullRequestBody = itemView.findViewById<TextView>(R.id.textViewPrBody)
            val pullRequestUserPhoto = itemView.findViewById<ImageView>(R.id.imageViewPrUserPhoto)
            val pullRequestUserName = itemView.findViewById<TextView>(R.id.textViewPrUserName)
            val pullRequestUserTrueName = itemView.findViewById<TextView>(R.id.textViewPrUserFullName)

            pullRequestTitle.text = pullRequest.title
            pullRequestBody.text = pullRequest.body
            pullRequestUserName.text = pullRequest.user.login
            pullRequestUserTrueName.text = pullRequest.head.repo.fullName
            Glide.with(itemView.context)
                    .load(pullRequest.user.avatarUrl)
                    .error(R.drawable.ic_unavailable)
                    .into(pullRequestUserPhoto)

            val repositoryHtmlUrl = pullRequest.htmlUrl

            itemView.setOnClickListener {
                onClick(repositoryHtmlUrl)
            }
        }
    }
}