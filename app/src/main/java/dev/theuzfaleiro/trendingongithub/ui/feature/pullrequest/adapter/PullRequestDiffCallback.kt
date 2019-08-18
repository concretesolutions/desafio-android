package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest

class PullRequestDiffCallback : DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean =
        oldItem == newItem
}