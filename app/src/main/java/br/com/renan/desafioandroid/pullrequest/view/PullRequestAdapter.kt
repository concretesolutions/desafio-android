package br.com.renan.desafioandroid.pullrequest.view

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.renan.desafioandroid.R
import br.com.renan.desafioandroid.model.data.PullRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter(pullRequestItemsList: List<PullRequest>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recyclerList: List<PullRequest> = pullRequestItemsList

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pull_request, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pullRequest: PullRequest) = with(itemView) {

            Glide.with(itemView.context)
                .load(pullRequest.user.avatarUrl)
                .into(ivPullRequestAvatar)

            tvPullRequestName.text = pullRequest.title
            tvPullRequestDescription.text =
                if (pullRequest.body.isEmpty()) context.getString(R.string.description_not_found) else pullRequest.body
            tvPullRequestUserName.text = pullRequest.user.login
            tvPullRequestFullName.text = pullRequest.user.login

            itemView.setOnClickListener {
                val openBrowser = Intent(Intent.ACTION_VIEW)
                openBrowser.data = Uri.parse(pullRequest.html_url)
                context.startActivity(openBrowser)
            }
        }
    }
}