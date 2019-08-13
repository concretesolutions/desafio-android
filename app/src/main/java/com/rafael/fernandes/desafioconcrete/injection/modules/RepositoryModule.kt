package com.rafael.fernandes.desafioconcrete.injection.modules

import com.rafael.fernandes.data.remote.repository.Repositories
import com.rafael.fernandes.domain.repositories.IRepositories
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideGitRepository(repositories: Repositories): IRepositories {
        return repositories
    }

}