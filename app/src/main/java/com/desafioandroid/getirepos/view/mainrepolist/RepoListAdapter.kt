package com.desafioandroid.getirepos.view.mainrepolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafioandroid.getirepos.databinding.ActivityRepoItemBinding
import com.desafioandroid.getirepos.data.dto.Repo
import com.desafioandroid.getirepos.data.utils.RepoTranslator

class RepoListAdapter(private val listener: RepoListActivityListener): RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    private var repos = ArrayList<RepoItem>()
    interface RepoListActivityListener {
        fun repoSelected(owner: String, repository: String)
    }

    fun setReposItems(newRepoItems: List<Repo>) {
        this.repos.addAll(RepoTranslator.translateRepoToRepoItem(newRepoItems))
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityRepoItemBinding.inflate(layoutInflater,parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(this.repos[position])
    }

    override fun getItemCount(): Int {
        return this.repos.size
    }

    inner class RepoViewHolder(private val binding: ActivityRepoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepoItem) {
            this.binding.hiddenRepoLink.text = repo.pullsUrl
            this.binding.repoDescriptionText.text = repo.description
            this.binding.repoTitleText.text = repo.name
            this.binding.repoForksCountText.text = repo.forksCount.toString()
            this.binding.repoStarsCountText.text = repo.stargazersCount.toString()
            this.binding.userNameText.text = repo.owner.login
            Glide.with(binding.root).load(repo.owner.avatarUrl).into(this.binding.userAvatarImageView)
            this.binding.root.setOnClickListener {
                listener.repoSelected(repo.owner.login, repo.name)
            }
        }
    }
}