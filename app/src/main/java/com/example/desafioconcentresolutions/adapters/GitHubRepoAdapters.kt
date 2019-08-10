package com.example.desafioconcentresolutions.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioconcentresolutions.adapters.GitHubRepoAdapters.GitHubRepoStatus.*
import com.example.desafioconcentresolutions.models.GitHubRepository
import kotlinx.android.synthetic.main.gitrepo_item_card.view.*

class GitHubRepoAdapters:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mStatus:GitHubRepoStatus = LOADING_GITHUB_REPO

    private var mRepoList:MutableList<GitHubRepository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return if(mStatus != DEFAULT_GITHUB_REPO) 1 else mRepoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(holder.itemViewType == DEFAULT_GITHUB_REPO.ordinal){
           var gitRepo = mRepoList[position]
           with(holder as GithubRepoViewHolder){
               username.text = gitRepo.username
               fullName.text = gitRepo.fullName
               repoDescription.text = gitRepo.description
               repoName.text = gitRepo.name

           }

       }
    }

    class GithubRepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var username = itemView.textView_gitrepoItemCard_userName
        var fullName = itemView.textView_gitrepoItemCard_fullName
        var starsIcon = itemView.imageView_gitrepoItemCard_iconStars
        var forksIcon = itemView.imageView_gitrepoItemCard_iconForks
        var repoDescription = itemView.textView_gitrepoItemCard_repoDescription
        var repoName = itemView.textView_gitrepoItemCard_repoName
    }
    enum class GitHubRepoStatus{
        EMPTY_GITHUB_REPO{
            override fun layout(): Int = 2
        },
        LOADING_GITHUB_REPO{
            override fun layout(): Int = 2
        },
        ERROR_GITHUB_REPO{
            override fun layout(): Int = 2
        },
        DEFAULT_GITHUB_REPO{
            override fun layout(): Int = 2
        };
        abstract fun layout(): Int
    }
}