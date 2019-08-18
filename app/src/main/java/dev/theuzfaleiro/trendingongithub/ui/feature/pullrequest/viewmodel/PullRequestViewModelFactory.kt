package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.repository.PullRequestRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullRequestViewModelFactory @Inject constructor(
    private val pullRequestRepository: PullRequestRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PullRequestViewModel(pullRequestRepository) as T
}