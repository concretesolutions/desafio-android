package com.losingtimeapps.javahub.ui.home.pullrequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.losingtimeapps.javahub.R
import com.losingtimeapps.presentation.model.PullRequestModel
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModel
import de.hdodenhof.circleimageview.CircleImageView

class PullRequestListAdapter(val pullRequestViewModel: PullRequestViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var pullRequestList: MutableList<PullRequestModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewCounterLayoutItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return RepositoryItemViewHolder(viewCounterLayoutItem)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryItemViewHolder) {
            val pullRequest = pullRequestList[position]
            holder.tvRepoName.text = pullRequest.title
            holder.tvRepoDescription.text = pullRequest.body

            holder.tvOwnerName.text = pullRequest.authorModel.name

            Glide.with(pullRequestViewModel.getContext()).load(pullRequest.authorModel.photoUrl)
                .apply(RequestOptions().error(R.drawable.ic_defauld_picture).placeholder(R.drawable.ic_defauld_picture))
                .into(holder.civOwnerPhoto)
        }
    }

    inner class RepositoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRepoName: TextView = itemView.findViewById(R.id.tv_pull_request_title)
        var tvRepoDescription: TextView = itemView.findViewById(R.id.tv_pull_request_body)
        var tvOwnerName: TextView = itemView.findViewById(R.id.tv_owner_name)
        var civOwnerPhoto: CircleImageView = itemView.findViewById(R.id.civ_owner_photo)

        init {
            itemView.setOnClickListener {
                pullRequestViewModel.onClickPullRequest(pullRequestList[adapterPosition])
            }
        }
    }

    fun updateData(repositoryList: List<PullRequestModel>) {
        pullRequestList.addAll(repositoryList)
        notifyDataSetChanged()
    }

    fun resetListAdapter() {
        pullRequestList.clear()
        notifyDataSetChanged()
    }

}