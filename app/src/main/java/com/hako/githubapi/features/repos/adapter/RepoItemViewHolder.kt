package com.hako.githubapi.features.repos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hako.githubapi.domain.entities.Repository
import kotlinx.android.synthetic.main.repo_list_item.view.*

class RepoItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var repo: Repository? = null
        set(value) {
            field = value
            view.repo_item_title.text = repo?.name
            view.repo_item_description.text = repo?.description
            view.repo_item_forks.text = repo?.forks
            view.repo_item_stars.text = repo?.stars
            view.repo_item_author.text = repo?.owner?.author
        }
}
