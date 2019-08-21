package dev.theuzfaleiro.trendingongithub.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository

class RepositoryAdapter(private val repositorySelected: (repository: Repository) -> Unit) :
    PagedListAdapter<Repository,
            RecyclerView.ViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as RepositoryViewHolder) {
            bindItemsToView(requireNotNull(getItem(position)), repositorySelected)
        }
    }
}