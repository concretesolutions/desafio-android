package com.jsouza.repopullrequests.presentation.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.extensions.loadImageUrl
import com.jsouza.repopullrequests.R
import com.jsouza.repopullrequests.databinding.PullRequestListItemBinding
import com.jsouza.repopullrequests.domain.model.PullRequests

class PullRequestsAdapter : RecyclerView.Adapter<PullRequestsAdapter.ViewHolder>() {

    private val pullList = mutableListOf<PullRequests>()

    fun submitList(
        newData: List<PullRequests>
    ) {
        if (pullList.isNotEmpty()) {
            pullList.clear()
        }
        pullList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(
            parent.context)
            .inflate(R.layout.pull_request_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pullList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.itemBind(pullList[position])
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = PullRequestListItemBinding.bind(itemView)

        fun itemBind(
            pullsResponse: PullRequests
        ) {
            binding.pullNameTextViewPullListItem.text = pullsResponse.title
            binding.repositoryDescriptionTextViewPullListItem.text = pullsResponse.body
            binding.usernameTextViewPullListItem.text = pullsResponse.owner?.username
            binding.repositoryCreatedDateTextViewPullListItem.text = pullsResponse.createdAt
            binding.ownerAvatarCircularImageViewPullListItem
                .loadImageUrl(pullsResponse
                    .owner
                    ?.avatar_url)

            itemView.setOnClickListener {
                callPullRequestOnBrowser(
                    pullsResponse.url ?: itemView.context
                        .getString(R.string.github_home_weblink_in_case_of_no_url_from_pull_request)
                )
            }
        }

        private fun callPullRequestOnBrowser(
            url: String
        ) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            itemView.context.startActivity(browserIntent)
        }
    }
}
