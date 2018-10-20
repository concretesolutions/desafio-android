package cl.mauledev.github.view.lists.viewholders

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import cl.mauledev.github.R
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.data.model.User
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.repo_item.view.*
import java.util.*

class RepoViewHolder(itemView: View,
                     val viewModel: MainViewModel?): RecyclerView.ViewHolder(itemView) {

    val titleView: TextView = itemView.repoTitle
    val statusView: TextView = itemView.repoStatus
    val starsView: TextView = itemView.stars

    fun init(item: Repo) {
        item.name?.let {
            initTitle(item.name)
        } ?: run {
            initTitle("")
        }
        initAuthorAndDate(item.author, item.createdAt)
        initAction(item)
        initStars(item)
    }

    private fun initStars(item: Repo) {
        starsView.text = String.format(itemView.context.resources
                .getQuantityString(R.plurals.stars, item.stars, item.stars))
    }

    private fun initAction(repo: Repo) {
        itemView.setOnClickListener {
            viewModel?.setSelectedRepo(repo)
        }
    }

    private fun initAuthorAndDate(author: User?, createdAt: Date) {
        statusView.text = "${author?.login} - ${DateUtils.getRelativeTimeSpanString(createdAt.time)}"
    }

    private fun initTitle(title: String?) {
        titleView.text = title
    }
}