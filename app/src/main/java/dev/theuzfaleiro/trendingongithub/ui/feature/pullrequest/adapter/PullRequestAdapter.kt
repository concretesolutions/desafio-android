package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest

class PullRequestAdapter(private val pullRequestSelected: (pullRequests: PullRequest) -> Unit) :
    ListAdapter<PullRequest, RecyclerView.ViewHolder>(PullRequestDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PullRequestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pull_request,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PullRequestViewHolder) {
            bindItemsToView(getItem(position)!!, pullRequestSelected)
        }
    }
}