package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pullRequestTitle = itemView.textViewPullRequestTitle
    private val pullRequestDescription = itemView.textViewPullRequestDescription
    private val usernameAvatar = itemView.imageViewUsername
    private val userLogin = itemView.textViewUserName

    fun bindItemsToView(
        pullRequest: PullRequest,
        pullRequestSelected: (pullRequests: PullRequest) -> Unit
    ) {

        pullRequestTitle.text = pullRequest.title
        pullRequestDescription.text = pullRequest.description

        usernameAvatar.load(pullRequest.user.avatarUrl) {
            crossfade(true)
            placeholder(android.R.drawable.alert_dark_frame)
            transformations(CircleCropTransformation())
        }

        userLogin.text = pullRequest.user.login

        itemView.setOnClickListener {
            pullRequestSelected(pullRequest)
        }
    }

}