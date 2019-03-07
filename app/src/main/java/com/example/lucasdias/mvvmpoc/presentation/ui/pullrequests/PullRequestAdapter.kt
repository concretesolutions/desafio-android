package com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lucasdias.mvvmpoc.R
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import com.example.lucasdias.mvvmpoc.presentation.extensions.loadUrl
import timber.log.Timber

class PullRequestAdapter(var view: PullRequestActivity): Adapter<PullRequestAdapter.ViewHolder>() {

    private var pullRequestList = ArrayList<PullRequest>()

    fun updatePullRequestList(pullRequest: ArrayList<PullRequest>) {
        this.pullRequestList = pullRequest
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.pull_request_list_item, parent, false)
        return ViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return pullRequestList.count()    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (pullRequestList.isNotEmpty()) {
            holder.bind(pullRequestList[position], view)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title by lazy { itemView.findViewById<TextView>(R.id.pullRequest_tvPullRequestTitle) }
        private val description by lazy { itemView.findViewById<TextView>(R.id.pullRequest_tvPullRequestDescription) }
        private val createdAt by lazy { itemView.findViewById<TextView>(R.id.pullRequest_tvPullRequestCreatedAt) }
        private val userName by lazy { itemView.findViewById<TextView>(R.id.pullRequest_tvPullRequestUserName) }
        private val userPicture by lazy { itemView.findViewById<ImageView>(R.id.pullRequest_ivPullRequestUserPicture) }

        fun bind(pullRequest: PullRequest, view: PullRequestActivity) {
            title.text = pullRequest.title
            description.text = pullRequest.description
            createdAt.text = pullRequest.getCreatedAtDateString()
            userName.text = pullRequest.user?.login
            userPicture.loadUrl(pullRequest.user?.avatarUrl)
            itemView.setOnClickListener {
                if(pullRequest.htmlUrl != ""){
                    Timber.i("PullRequest clicked.")
                    view.callPullRequestOnBrowser(pullRequest.htmlUrl ?: "https://www.google.com")
                }
            }
        }
    }
}