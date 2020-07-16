package com.jsouza.githubrepos.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.githubrepos.R
import com.jsouza.githubrepos.databinding.RepositoryListItemBinding
import com.jsouza.githubrepos.presentation.model.Repository

class RepositoryCatalogAdapter : RecyclerView.Adapter<RepositoryCatalogAdapter.ViewHolder>() {

    private val repositories = mutableListOf<Repository>(
        Repository("CS Notes", "Lorem ipsum sit dolor amer", "The BoB", "Uncle Bob"),
        Repository("Butter Knife", "Lorem ipsum sit dolor amer", "jake wharton", "mr jake"),
        Repository("CS Notes", "Lorem ipsum sit dolor amer", "jsouza987", " repository"),
        Repository("Butter Knife", "Lorem ipsum sit dolor amer", "G0ll1m", "Smeagol"),
        Repository("CS Notes", "Lorem ipsum sit dolor amer", "Spider Pig", "homer simpson"),
        Repository("Butter Knife", "Lorem ipsum sit dolor amer", "jsouza678", "repository name")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_list_item,
                parent,
                false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBind(repositories[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RepositoryListItemBinding.bind(itemView)

        fun itemBind(repository: Repository) {
            binding.repositoryNameTextViewListItem.text = repository.repoName
            binding.repositoryDescriptionTextViewListItem.text = repository.description
            binding.usernameTextViewListItem.text = repository.userName
            binding.fullNameTextViewListItem.text = repository.fullName
        }
    }
}
