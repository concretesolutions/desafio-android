package com.example.desafioconcentresolutions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.adapters.GitHubRepoAdapter.GitHubRepoStatus.*
import com.example.desafioconcentresolutions.models.GitHubRepo
import kotlinx.android.synthetic.main.gitrepo_item_card.view.*

class GitHubRepoAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mStatus: GitHubRepoStatus = LOADING_GITHUB_REPO
    private val mStatusAdapter = StatusAdapterDelegate()

    private var mRepoList: MutableList<GitHubRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (mStatus) {
            EMPTY_GITHUB_REPO,
            LOADING_GITHUB_REPO,
            ERROR_GITHUB_REPO -> mStatusAdapter.onCreateViewHolder(
                LayoutInflater.from(parent.context),
                parent,
                mStatus.layout()
            )
            DEFAULT_GITHUB_REPO -> GithubRepoViewHolder(LayoutInflater.from(parent.context).inflate(
                mStatus.layout(),
                parent,
                false
            ))
        }
    }

    override fun getItemCount(): Int {
        return if (mStatus != DEFAULT_GITHUB_REPO) 1 else mRepoList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mStatus.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == DEFAULT_GITHUB_REPO.ordinal) {
            val gitRepo = mRepoList[position]
            with(holder as GithubRepoViewHolder) {
                username.text = gitRepo.owner.username
                fullName.text = gitRepo.fullName
                repoDescription.text = gitRepo.description
                repoName.text = gitRepo.name
                stars.text = gitRepo.stars.toString()
                forks.text = gitRepo.forks.toString()

                loadImage(context, gitRepo.owner.avatar_url, avatar)
            }
        }
    }

    fun updateGitHubList(list: List<GitHubRepo>) {
        mStatus = if (list.isEmpty()) EMPTY_GITHUB_REPO else DEFAULT_GITHUB_REPO
        mRepoList.clear()
        mRepoList.addAll(list)

        notifyDataSetChanged()
    }

    fun setStatus(status: GitHubRepoStatus) {
        mStatus = status

        notifyDataSetChanged()
    }

    private fun loadImage(context:Context, imageUrl:String, view:ImageView){
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .crossFade()
            .into(view)

    }

    class GithubRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username = itemView.textView_gitrepoItemCard_userName
        var fullName = itemView.textView_gitrepoItemCard_fullName
        var stars = itemView.textView_gitrepoItemCard_starsNumber
        var forks = itemView.textView_gitrepoItemCard_forksNumber
        var repoDescription = itemView.textView_gitrepoItemCard_repoDescription
        var repoName = itemView.textView_gitrepoItemCard_repoName
        var avatar = itemView.imageView_gitrepoItemCard_photo
    }

    enum class GitHubRepoStatus {
        EMPTY_GITHUB_REPO {
            override fun layout(): Int = R.layout.loading_status
        },
        LOADING_GITHUB_REPO {
            override fun layout(): Int = R.layout.loading_status
        },
        ERROR_GITHUB_REPO {
            override fun layout(): Int = R.layout.loading_status
        },
        DEFAULT_GITHUB_REPO {
            override fun layout(): Int = R.layout.gitrepo_item_card
        };

        abstract fun layout(): Int
    }
}