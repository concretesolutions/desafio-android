package cl.mauledev.github.di.modules

import cl.mauledev.github.data.datasources.remote.RemoteDataSource
import cl.mauledev.github.data.datasources.remote.api.RepoAPI
import cl.mauledev.github.data.repositories.RepoRepository
import cl.mauledev.github.view.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class RepoModule(val viewModel: MainViewModel) {

    @Provides
    fun providesRemoteDataSource(repoAPI: RepoAPI): RemoteDataSource {
        return RemoteDataSource(repoAPI)
    }

    @Provides
    fun providesRepository(remoteDataSource: RemoteDataSource): RepoRepository {
        return RepoRepository(remoteDataSource)
    }
}