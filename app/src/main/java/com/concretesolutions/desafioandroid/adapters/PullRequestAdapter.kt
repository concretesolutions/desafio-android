package com.concretesolutions.desafioandroid.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.concretesolutions.desafioandroid.databinding.PullrequestItemBinding
import com.concretesolutions.desafioandroid.model.PullRequest
import com.concretesolutions.desafioandroid.viewmodel.PullsViewModel


class PullRequestAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PullRequestAdapter.PullRequestAdapterViewHolder>() {

    private var pullRequests: MutableList<PullRequest> = arrayListOf()

    fun updatePullRequests(repos: List<PullRequest>) {
        pullRequests.addAll(repos)
        notifyDataSetChanged()
    }

    fun setPullRequests(repos: List<PullRequest>) {
        pullRequests = repos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int)
            : PullRequestAdapterViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = PullrequestItemBinding.inflate(inflater, viewGroup, false)

        return PullRequestAdapterViewHolder(binding, listener)

    }

    override fun getItemCount() = pullRequests.count()

    override fun onBindViewHolder(holder: PullRequestAdapterViewHolder, position: Int) {
        holder.bind(pullRequests[position])
    }

    fun getPullRequests(): MutableList<PullRequest> {
        return pullRequests
    }


    class PullRequestAdapterViewHolder(
        private val binding: PullrequestItemBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var pullRequest: PullRequest

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(pullRequest)
            }
        }

        fun bind(pr: PullRequest) {
            pullRequest = pr
            binding.pull = PullsViewModel(pr)
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pullRequest: PullRequest)
    }
}