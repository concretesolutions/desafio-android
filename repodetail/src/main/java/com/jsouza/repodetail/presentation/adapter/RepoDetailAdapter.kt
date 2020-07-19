package com.jsouza.repodetail.presentation.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.extensions.loadImageUrl
import com.jsouza.repodetail.R
import com.jsouza.repodetail.data.remote.response.PullsResponse
import com.jsouza.repodetail.databinding.PullRequestListItemBinding

class RepoDetailAdapter : RecyclerView.Adapter<RepoDetailAdapter.ViewHolder>() {

    private val pullList = mutableListOf<PullsResponse>()

    fun submitList(
        newData: List<PullsResponse>
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

        fun itemBind(pullsResponse: PullsResponse) {
            binding.repositoryNameTextViewPullListItem.text = pullsResponse.title
            binding.repositoryDescriptionTextViewPullListItem.text = pullsResponse.body
            binding.usernameTextViewPullListItem.text = pullsResponse.owner?.username
            binding.repositoryCreatedDateTextViewPullListItem.text = pullsResponse.getCreatedAtDateString()
            binding.ownerAvatarCircularImageViewPullListItem
                .loadImageUrl(pullsResponse
                    .owner
                    ?.avatar_url)

            itemView.setOnClickListener {
                callPullRequestOnBrowser(
                    pullsResponse.url ?: itemView.context
                        .getString(R.string.google_weblink_in_case_of_no_url_from_pull_request)
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
