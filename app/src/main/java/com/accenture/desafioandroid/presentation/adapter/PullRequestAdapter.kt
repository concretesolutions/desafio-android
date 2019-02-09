package com.accenture.desafioandroid.presentation.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import android.view.LayoutInflater
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.mvvm.model.PullRequest
import com.accenture.desafioandroid.presentation.adapter.viewholder.PullRequestViewHolder
import com.accenture.desafioandroid.presentation.listerner.PullRsListener

class PullRequestAdapter(val context: Context, diffCallback: DiffUtil.ItemCallback<PullRequest>, private val pullListener: PullRsListener):
    PagedListAdapter<PullRequest, PullRequestViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PullRequestViewHolder, position: Int) {

        val pullRequest = getItem(position)
        if (pullRequest != null)
            viewHolder.setDataPull(pullRequest)

        viewHolder.content.setOnClickListener {
            val url = pullRequest!!.url!!
            pullListener.goDetails(url)
        }
    }

}