package com.losingtimeapps.javahub.ui.home.repository

import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase
import com.losingtimeapps.presentation.ui.home.repository.RepositoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {


    @Provides
    fun provideRepositoryViewModelFactory(
        getGitHubRepoUseCase: GetGitHubRepoUseCase
    ) = RepositoryViewModelFactory(getGitHubRepoUseCase)
}