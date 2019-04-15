package com.dobler.desafio_android.ui.pull.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dobler.desafio_android.data.model.RepositoryPullRequest
import com.dobler.desafio_android.databinding.ListRvPullRequestBinding
import kotlinx.android.synthetic.main.list_rv_user.view.*

class PullRequestListAdapter() :
    RecyclerView.Adapter<PullRequestListAdapter.PullRequestViewHolder>() {

    var dataset: ArrayList<RepositoryPullRequest> = ArrayList();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestListAdapter.PullRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ListRvPullRequestBinding = ListRvPullRequestBinding.inflate(
            inflater,
            parent,
            false
        )

        return PullRequestViewHolder(binding)
    }

    fun addPullRequestsList(data: List<RepositoryPullRequest>) {
        dataset.addAll(data)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {

        holder.bind(dataset[position])

        Glide.with(holder.itemView.context)
            .load(dataset[position].user.avatar_url)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.ivUserImage)
    }

    override fun getItemCount() = dataset.size

    class PullRequestViewHolder(val view: ListRvPullRequestBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(pullRequest: RepositoryPullRequest) {
            view.pulls = pullRequest
            view.executePendingBindings()
        }
    }

}
