package com.jsouza.repocatalog.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.extensions.loadImageUrl
import com.jsouza.githubrepos.R
import com.jsouza.githubrepos.databinding.RepositoryListItemBinding
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository

class RepositoryCatalogAdapter : RecyclerView.Adapter<RepositoryCatalogAdapter.ViewHolder>() {

    private val repositories = mutableListOf<Repository>()

    fun submitList(newData: List<Repository>) {
        if (repositories.isNotEmpty()) {
            repositories.clear()
        }
        repositories.addAll(newData)
        notifyDataSetChanged()
    }

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
            binding.repositoryNameTextViewListItem.text = repository.name
            binding.repositoryDescriptionTextViewListItem.text = repository.description
            binding.usernameTextViewListItem.text = repository.owner?.login
            binding.fullNameTextViewListItem.text = repository.fullName
            binding.ownerAvatarCircularImageViewListItem.loadImageUrl(repository.owner?.avatarUrl)
        }
    }
}
