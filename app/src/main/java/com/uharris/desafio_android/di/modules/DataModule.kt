package com.uharris.desafio_android.di.modules

import com.uharris.desafio_android.BuildConfig
import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.remote.RepositoriesRemoteImpl
import com.uharris.desafio_android.data.services.GithubServiceFactory
import com.uharris.desafio_android.data.services.RepositoriesServices
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun provideRepositoryService(): RepositoriesServices {
            return GithubServiceFactory.makeService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindRepositoriesRemote(repositoriesRemote: RepositoriesRemoteImpl): RepositoriesRemote
}