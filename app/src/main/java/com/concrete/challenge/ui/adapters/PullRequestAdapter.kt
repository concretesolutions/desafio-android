package com.concrete.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.ui.viewholders.PullRequestViewHolder

class PullRequestAdapter(
    private val manager: AdapterManager
) : RecyclerView.Adapter<PullRequestViewHolder>() {

    interface AdapterManager {
        fun onPullRequestClicked(pullRequestClicked: PullRequestEntity)
    }

    private val pullRequestList = mutableListOf<PullRequestEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pull_request, parent, false)

        return PullRequestViewHolder(view, manager)
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]

        holder.bind(item)
    }

    fun addItems(list: List<PullRequestEntity>) {
        pullRequestList.addAll(list)
        notifyDataSetChanged()
    }

}