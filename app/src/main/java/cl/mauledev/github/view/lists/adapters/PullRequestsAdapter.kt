package cl.mauledev.github.view.lists.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import cl.mauledev.github.R
import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.view.lists.viewholders.PullRequestViewHolder
import cl.mauledev.github.view.viewmodels.MainViewModel

class PullRequestsAdapter(val viewModel: MainViewModel?) : ListAdapter<PullRequest, PullRequestViewHolder>(differCallback) {

    companion object {

        val differCallback = object: DiffUtil.ItemCallback<PullRequest>() {

            override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pr_item, parent, false)
        return PullRequestViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.init(getItem(position))
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun getTopic(position: Int?): PullRequest? {
        position?.let {
            return getItem(position)
        }

        return null
    }
}