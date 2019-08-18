package dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource

import androidx.paging.DataSource
import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository

class RepositoryDataSourceFactory(private val gitHubEndpoint: GitHubEndpoint) :
    DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> = RepositoryDataSource(gitHubEndpoint)
}