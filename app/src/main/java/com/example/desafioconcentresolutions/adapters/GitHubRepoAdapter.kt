package com.example.desafioconcentresolutions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.models.GitHubRepo
import kotlinx.android.synthetic.main.gitrepo_item_card.view.*

class GitHubRepoAdapter(private val context: Context) :
    PagedListAdapter<GitHubRepo, RecyclerView.ViewHolder>(GitHubDiffCall) {

    private var mListener: GitHubRepoListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GithubRepoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.gitrepo_item_card,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gitRepo = getItem(position)
        with(holder as GithubRepoViewHolder) {
            username.text = gitRepo?.owner?.username
            repoDescription.text = gitRepo?.description
            repoName.text = gitRepo?.name
            stars.text = gitRepo?.stars.toString()
            forks.text = gitRepo?.forks.toString()

            card.setOnClickListener {
                gitRepo?.let {
                    mListener?.onRepoClicked(it.owner.username, it.name)
                }
            }

            loadImage(context, gitRepo?.owner?.avatar_url ?: "", avatar)

        }
    }

    private fun loadImage(context: Context, imageUrl: String, view: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)

    }

    fun setGitHubRepoListener(listener: GitHubRepoListener) {
        mListener = listener
    }

    class GithubRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username = itemView.textView_gitrepoItemCard_userName
        var stars = itemView.textView_gitrepoItemCard_starsNumber
        var forks = itemView.textView_gitrepoItemCard_forksNumber
        var repoDescription = itemView.textView_gitrepoItemCard_repoDescription
        var repoName = itemView.textView_gitrepoItemCard_repoName
        var avatar = itemView.imageView_gitrepoItemCard_photo
        var card = itemView.constraintLayout_gitRepoItemCard_card
    }

    interface GitHubRepoListener {
        fun onRepoClicked(ownerName: String, repo: String)
    }

    companion object {
        val GitHubDiffCall = object : DiffUtil.ItemCallback<GitHubRepo>() {
            override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem.fullName == newItem.fullName
            }

            override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem.fullName == newItem.fullName
            }

        }
    }
}