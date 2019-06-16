package br.com.arthur.githubapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.githubapp.databinding.ItemPullRequestListBinding
import br.com.arthur.githubapp.model.PullRequest
import br.com.arthur.githubapp.util.loadUrl

class PullRequestAdapter : RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    private var pullList: List<PullRequest> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val holder = ItemPullRequestListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestViewHolder(holder)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = pullList[position]
        holder.bind.pullRequestTitle.text = pullRequest.title
        holder.bind.pullRequestBody.text = pullRequest.body
        holder.bind.userImage.loadUrl(pullRequest.user.avatarUrl)
        holder.bind.userName.text = pullRequest.user.login
    }

    override fun getItemCount(): Int {
        return pullList.size
    }

    fun setPullList(pulls: List<PullRequest>) {
        pullList = pulls
        this.notifyDataSetChanged()
    }

    inner class PullRequestViewHolder(internal var bind: ItemPullRequestListBinding) :
        RecyclerView.ViewHolder(bind.root)

}