package com.desafioandroid.getirepos.view.mainrepolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desafioandroid.getirepos.databinding.ActivityRepoItemBinding
import com.desafioandroid.getirepos.data.dto.Repo
import com.desafioandroid.getirepos.data.utils.RepoTranslator

class RepoListAdapter(private val listener: RepoListActivityListener): RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    private var repos = ArrayList<RepoItem>()
    interface RepoListActivityListener {
        fun repoSelected(repoLink: String)
    }

    fun setReposItems(newRepoItems: List<Repo>) {
        this.repos = RepoTranslator.translateRepoToRepoItem(newRepoItems)
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
            //TODO:Falta setear link de imagen de avatar
            this.binding.root.setOnClickListener {
                listener.repoSelected(this.binding.hiddenRepoLink.text.toString())
            }
        }
    }
}