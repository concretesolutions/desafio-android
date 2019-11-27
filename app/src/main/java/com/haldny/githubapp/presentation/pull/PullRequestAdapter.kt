package com.haldny.githubapp.presentation.pull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.haldny.githubapp.R
import com.haldny.githubapp.common.extension.dateFormat
import com.haldny.githubapp.common.extension.decimalFormat
import com.haldny.githubapp.domain.model.Repository
import com.haldny.githubapp.domain.model.ResponsePullRequest
import kotlinx.android.synthetic.main.pull_request.view.*
import kotlinx.android.synthetic.main.repository.view.*

class PullRequestAdapter(private val onItemClickListener: ((ResponsePullRequest) -> Unit)) :
    RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    private var pullRequests = ArrayList<ResponsePullRequest>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pull_request, viewGroup, false)
        return PullRequestViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = pullRequests.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {

        val item = pullRequests[position]
        holder.bindView(item)
    }

    fun addList(listOfPullRequests: List<ResponsePullRequest>) {
        this.pullRequests.addAll(listOfPullRequests)
        notifyItemChanged(this.pullRequests.size - listOfPullRequests.size, this.pullRequests.size)
    }

    fun clearList() {
        this.pullRequests.clear()
        notifyDataSetChanged()
    }

    class PullRequestViewHolder(private val view: View,
                                private val onItemClickListener: ((ResponsePullRequest) -> Unit)):
        RecyclerView.ViewHolder(view) {

        private val title: TextView = view.tv_pull_request_title
        private val description: TextView = view.tv_pull_request_description
        private val owner: TextView = view.tv_pull_request_owner
        private val date: TextView = view.tv_pull_request_date
        private val avatar: ImageView = view.iv_pull_request_avatar

        fun bindView(pullRequest: ResponsePullRequest) = with(view) {
            title.text = pullRequest.title
            description.text = pullRequest.body

            owner?.text = pullRequest?.owner?.login
            date.text = pullRequest.date.dateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            val drawableImageDefault = R.drawable.github

            Glide.with(context)
                .load(pullRequest?.owner?.avatarUrl)
                .placeholder(drawableImageDefault)
                .error(drawableImageDefault)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar)

            this.setOnClickListener {
                onItemClickListener(pullRequest)
            }
        }
    }
}