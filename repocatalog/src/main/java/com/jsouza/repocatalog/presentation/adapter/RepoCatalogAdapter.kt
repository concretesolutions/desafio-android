package com.jsouza.repocatalog.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.extensions.loadImageUrl
import com.jsouza.repocatalog.R
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.mapper.RepoMapper
import com.jsouza.repocatalog.data.remote.response.Repository
import com.jsouza.repocatalog.databinding.RepositoryListItemBinding
import com.jsouza.repocatalog.domain.`typealias`.StartRepoDetail
import com.jsouza.repocatalog.utils.DiffUtilCallback
import java.text.DecimalFormat
import java.text.NumberFormat

class RepoCatalogAdapter(
    private val startDetailActivity: StartRepoDetail
) : PagingDataAdapter<RepositoryEntity, RepoCatalogAdapter.ViewHolder>(DiffUtilCallback()) {

    companion object {
        private const val NUMBER_PATTERN = "#,###"
        private const val COMMA_SEPARATOR = ","
        private const val DOT_SEPARATOR = "."
    }

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
        private lateinit var repo: Repository
        init {
            itemView.setOnClickListener {
                Log.i("Api adapter", "${repo.name}, ${repo.owner?.login}")
                startDetailActivity(
                    repo.name,
                    repo.owner?.login
                )
            }
        }

        private var formatter: NumberFormat = DecimalFormat(NUMBER_PATTERN)

        fun itemBind(repoEntity: RepositoryEntity) {
            this.repo = RepoMapper.toDomainModel(repoEntity)
            binding.repositoryNameTextViewListItem.text = repo.name
            binding.repositoryDescriptionTextViewListItem.text = repo.description
            binding.fullNameTextViewListItem.text = repo.fullName

            val owner = repo.owner
            binding.usernameTextViewListItem.text = owner?.login

            val forksCountFormatted = formatter.format(
                repo.forksCount
            ).toString().replace(
                COMMA_SEPARATOR,
                DOT_SEPARATOR
            )
            binding.repositoryBranchCountTextViewListItem.text = forksCountFormatted

            val stargazersCountFormatted = formatter.format(
                repo.stargazersCount
            ).toString().replace(
                COMMA_SEPARATOR,
                DOT_SEPARATOR
            )
            binding.repositoryStarCountTextViewListItem.text = stargazersCountFormatted

            binding.ownerAvatarCircularImageViewListItem
                .loadImageUrl(owner
                    ?.avatarUrl)
        }
    }
}
