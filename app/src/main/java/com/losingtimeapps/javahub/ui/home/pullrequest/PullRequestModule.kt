package com.losingtimeapps.javahub.ui.home.pullrequest

import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PullRequestModule {

    @Provides
    fun providePullRequestViewModelFactory(getGitHubPullRequestsUseCase: GetGitHubPullRequestsUseCase
    ) = PullRequestViewModelFactory(
            getGitHubPullRequestsUseCase
        )

}