package cl.mauledev.github.view.lists.viewholders

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.User
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.pr_item.view.*
import kotlinx.android.synthetic.main.repo_item.view.*
import java.util.*

class PullRequestViewHolder(itemView: View,
                            val viewModel: MainViewModel?): RecyclerView.ViewHolder(itemView) {

    val titleView: TextView = itemView.prTitle
    val statusView: TextView = itemView.prStatus

    fun init(item: PullRequest) {
        item.title?.let {
            initTitle(item.title)
        } ?: run {
            initTitle("")
        }
        initAuthorAndDate(item.user, item.createdAt)
        initAction(item)
    }

    private fun initAction(pullRequest: PullRequest) {
        itemView.setOnClickListener {
            viewModel?.setSelectedPullRequest(pullRequest)
        }
    }

    private fun initAuthorAndDate(author: User?, createdAt: Date) {
        statusView.text = "${author?.login} - ${DateUtils.getRelativeTimeSpanString(createdAt.time)}"
    }

    private fun initTitle(title: String?) {
        titleView.text = title
    }
}