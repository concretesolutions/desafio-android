package com.jsouza.repocatalog.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.extensions.loadImageUrl
import com.jsouza.repocatalog.R
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.local.utils.RepoTypeConverter
import com.jsouza.repocatalog.databinding.RepositoryListItemBinding
import com.jsouza.repocatalog.utils.DiffUtilCallback

class RepoCatalogAdapter() : PagingDataAdapter<RepositoryEntity, RepoCatalogAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.repository_list_item,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.itemBind(repoItem)
        }
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = RepositoryListItemBinding.bind(itemView)

        fun itemBind(repository: RepositoryEntity) {
            binding.repositoryNameTextViewListItem.text = repository.name
            binding.repositoryDescriptionTextViewListItem.text = repository.description
            val owner = RepoTypeConverter.toOwner(repository.owner)
            binding.usernameTextViewListItem.text = owner?.login
            binding.fullNameTextViewListItem.text = repository.fullName
            binding.ownerAvatarCircularImageViewListItem
                .loadImageUrl(owner
                    ?.avatarUrl)
        }
    }
}
