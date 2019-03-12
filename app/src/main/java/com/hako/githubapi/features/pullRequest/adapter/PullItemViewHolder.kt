package com.hako.githubapi.features.pullRequest.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hako.githubapi.domain.entities.PullRequest
import kotlinx.android.synthetic.main.pull_list_item.view.*

class PullItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var pull: PullRequest? = null
        set(value) {
            field = value
            view.pull_item_title.text = pull?.title
            view.pull_item_description.text = pull?.description
            view.pull_item_author.text = pull?.user?.author
        }
}
