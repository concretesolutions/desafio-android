package com.dobler.desafio_android.ui.pull.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.data.model.RepositoryPullRequest
import com.dobler.desafio_android.databinding.ListRvPullRequestBinding
import com.dobler.desafio_android.databinding.ListRvRepositoryBinding
import com.dobler.desafio_android.util.paging.NetworkState
import kotlinx.android.synthetic.main.list_rv_user.view.*

class PullRequestListAdapter() :
    RecyclerView.Adapter<PullRequestListAdapter.PullRequestViewHolder>() {

    var myDataset: ArrayList<RepositoryPullRequest> = ArrayList();


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
        myDataset.addAll(data)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {

        holder.bind(myDataset[position])

        Glide.with(holder.itemView.context)
            .load(myDataset[position].user.avatar_url)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.ivUserImage)

    }

    override fun getItemCount() = myDataset.size

    class PullRequestViewHolder(val view: ListRvPullRequestBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(pullRequest: RepositoryPullRequest) {
            view.pulls = pullRequest
            view.executePendingBindings()
        }
    }


}