package dev.theuzfaleiro.trendingongithub.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val movieSelected: (movieDetail: Repository) -> Unit) :
    PagedListAdapter<Repository,
            RecyclerView.ViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpcomingMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as UpcomingMovieViewHolder) {
            bindItemsToView(getItem(position)!!, movieSelected)
        }
    }

    class UpcomingMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repositoryLogo = itemView.imageViewRepositoryLogo
        private val repositoryName = itemView.textViewRepositoryName
        private val userName = itemView.textViewRepositoryUserName
        private val repositoryDescription = itemView.textViewRepositoryDescription
        private val forkCount = itemView.textViewForkCount
        private val starCount = itemView.textViewStarCount

        fun bindItemsToView(
            repository: Repository,
            repositorySelected: (movieDetail: Repository) -> Unit
        ) {
            repositoryLogo.load(repository.owner.avatarUrl) {
                crossfade(true)
                placeholder(android.R.drawable.alert_dark_frame)
                transformations(CircleCropTransformation())
            }

            repositoryName.text = repository.name
            userName.text = repository.owner.userName
            repositoryDescription.text = repository.description
            forkCount.text = repository.forksCount()
            starCount.text = repository.starsCount()


            itemView.setOnClickListener {
                repositorySelected(repository)
            }
        }
    }
}