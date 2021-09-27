package com.concrete.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.ui.viewholders.PullRequestViewHolder

class PullRequestAdapter(private val pullRequestList: List<PullRequestEntity>) : RecyclerView.Adapter<PullRequestViewHolder>() {

    //private val pullRequestList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pull_request, parent, false)

        return PullRequestViewHolder(view)
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]
        holder.bind(item)
    }


}