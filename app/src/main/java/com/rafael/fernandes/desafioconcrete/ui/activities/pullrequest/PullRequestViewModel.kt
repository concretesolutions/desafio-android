package com.rafael.fernandes.desafioconcrete.ui.activities.pullrequest

import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.presentation.resources.Resource
import com.rafael.fernandes.desafioconcrete.ui.base.BaseViewModel
import com.rafael.fernandes.desafioconcrete.ui.custom.DefaultSingleObserver
import com.rafael.fernandes.domain.interector.GetPullRequest
import com.rafael.fernandes.domain.model.GitPullRequestRequest
import com.rafael.fernandes.domain.model.GitPullRequests
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(private val getPullRequest: GetPullRequest) : BaseViewModel() {

    val mObservableGitPullRequest = MutableLiveData<Resource<List<GitPullRequests>>>()

    fun listPullRequests(name: String, repository: String) {
        val request = GitPullRequestRequest(name, repository)
        getPullRequest.executeSingle(
            object : DefaultSingleObserver<List<GitPullRequests>>(mObservableGitPullRequest) {},
            request
        )
    }

    override fun myOnCleared() {}

}