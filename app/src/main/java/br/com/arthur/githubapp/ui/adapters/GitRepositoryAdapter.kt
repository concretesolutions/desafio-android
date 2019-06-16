package br.com.arthur.githubapp.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthur.githubapp.databinding.ItemRepositoryListBinding
import br.com.arthur.githubapp.model.GitRepository
import br.com.arthur.githubapp.ui.activities.PullRequestActivity
import br.com.arthur.githubapp.util.constants.Constants.KEY_OWNER_NAME
import br.com.arthur.githubapp.util.constants.Constants.KEY_REPOSITORY_NAME
import br.com.arthur.githubapp.util.loadUrl

class GitRepositoryAdapter : RecyclerView.Adapter<GitRepositoryAdapter.GitRepositoryViewHolder>() {

    private var repositoryList = mutableListOf<GitRepository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryViewHolder {
        val holder = ItemRepositoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitRepositoryViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: GitRepositoryViewHolder, position: Int) {
        val repository = repositoryList[position]
        holder.bind.ownerImage.loadUrl(repository.owner.avatarUrl)
        holder.bind.ownerName.text = repository.owner.login
        holder.bind.forkValue.text = repository.forksCount.toString()
        holder.bind.starsValue.text = repository.stargazersCount.toString()
        holder.bind.repositoryName.text = repository.name
        holder.bind.repositoryDescription.text = repository.description
        holder.configureClick(repository)
    }

    fun setGitRepositoryList(list: List<GitRepository>) {
        repositoryList.clear()
        repositoryList.addAll(list)
        this.notifyDataSetChanged()
    }

    inner class GitRepositoryViewHolder(internal var bind: ItemRepositoryListBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun configureClick(repository: GitRepository) {
            bind.root.setOnClickListener { cell ->
                val detailIntent = Intent(cell.context, PullRequestActivity::class.java)
                detailIntent.putExtra(KEY_OWNER_NAME, repository.owner.login)
                detailIntent.putExtra(KEY_REPOSITORY_NAME, repository.name)
                cell.context.startActivity(detailIntent)
            }
        }
    }

}