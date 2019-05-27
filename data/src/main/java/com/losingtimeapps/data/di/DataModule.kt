package com.losingtimeapps.data.di

import com.losingtimeapps.data.mapper.ResponseMapper
import com.losingtimeapps.data.remote.GitHubRepositoryImp
import com.losingtimeapps.data.remote.GitHubService
import com.losingtimeapps.domain.boundary.GitHubRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideResponseMapper(): ResponseMapper = ResponseMapper()


    @Provides
    fun provideGitHubRepositoryImp(
        gitHubService: GitHubService,
        responseMapper: ResponseMapper
    ): GitHubRepository = GitHubRepositoryImp(gitHubService, responseMapper)
}