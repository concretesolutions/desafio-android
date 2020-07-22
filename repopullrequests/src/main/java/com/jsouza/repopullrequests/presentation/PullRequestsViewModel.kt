package com.jsouza.repopullrequests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsouza.repopullrequests.domain.usecase.FetchPullRequestsFromApi
import com.jsouza.repopullrequests.domain.usecase.GetPullRequestsFromDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class PullRequestsViewModel(
    internal val coroutineContext: CoroutineDispatcher,
    internal val fetchPullRequestsFromApi: FetchPullRequestsFromApi,
    internal val getPullRequestsFromDatabase: GetPullRequestsFromDatabase
) : ViewModel() {

    fun returnPullRequestsOnLiveData(
        repositoryId: Long
    ) = getPullRequestsFromDatabase.invoke(repositoryId)

    fun loadPullRequestsOfRepository(
        userName: String?,
        repoName: String?,
        repoId: Long?
    ) {
        if (userName != null && repoName != null && repoId != null) {
            viewModelScope.launch(coroutineContext) {
                fetchPullRequestsFromApi.invoke(userName, repoName, repoId)
            }
        }
    }
}
