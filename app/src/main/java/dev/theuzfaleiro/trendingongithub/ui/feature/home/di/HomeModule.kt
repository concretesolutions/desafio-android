package dev.theuzfaleiro.trendingongithub.ui.feature.home.di

import dagger.Module
import dagger.Provides
import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource.RepositoryDataSourceFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel.HomeViewModelFactory

@Module
object HomeModule {

    @Provides
    @JvmStatic
    fun providesHomeRepository(repositoryDataSourceFactory: RepositoryDataSourceFactory) =
        HomeRepository(repositoryDataSourceFactory)

    @Provides
    @JvmStatic
    fun providesHomeViewModelFactory(homeRepository: HomeRepository) =
        HomeViewModelFactory(homeRepository)

    @Provides
    @JvmStatic
    fun providesRepositoryDataSourceFactory(gitHubEndpoint: GitHubEndpoint) =
        RepositoryDataSourceFactory(gitHubEndpoint)
}