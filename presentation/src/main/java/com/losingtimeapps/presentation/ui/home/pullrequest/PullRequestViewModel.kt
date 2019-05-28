package com.losingtimeapps.presentation.ui.home.pullrequest

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.losingtimeapps.common.BaseViewModel
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.boundary.ResponseBoundary
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.presentation.model.PullRequestModel
import com.losingtimeapps.presentation.model.RepositoryModel
import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase
import com.losingtimeapps.presentation.mapper.PullRequestModelMapper
import com.losingtimeapps.presentation.ui.home.repository.RepositoryView

class PullRequestViewModel(
    private val getGitHubPullRequestsUseCase: GetGitHubPullRequestsUseCase
) : BaseViewModel(), ResponseBoundary {

    private var view: PullRequestView? = null
    private var ownerName: String = ""
    private var repoName: String = ""
    private val open = "open"

    fun setView(view: PullRequestView, ownerName: String, repoName: String) {
        this.view = view
        this.ownerName = ownerName
        this.repoName = repoName
    }

    private val pullRequestListData = mutableListOf<PullRequest>()

    override fun onGetRepository(repoListData: List<Repository>) {

    }

    override fun onGetPullRequest(pullRequestListData: List<PullRequest>) {
        this.pullRequestListData.addAll(pullRequestListData)
        if (this.pullRequestListData.size == 0) {
            view?.notDataLoaded(true)
        } else {
            view?.updateRepoLiveData(PullRequestModelMapper().apply(pullRequestListData))
            updateOpenClosedNumber()
            view?.notDataLoaded(false)
        }
        view?.showProgress(false)
    }

    fun updateOpenClosedNumber() {
        var closedCount = 0
        var openCount = 0
        pullRequestListData.forEach {
            if (it.state == open)
                openCount++
            else
                closedCount++
        }
        view?.updateOpenClosedNumber(openCount, closedCount)
    }

    override fun onError(error: Error) {
        view?.showSnackbarError(error)
        view?.showProgress(false)
    }

    fun onClickPullRequest(pullRequestModel: PullRequestModel) {
        view?.navigateToPullRequestView(pullRequestModel.pullRequestUrl)
    }

    fun getContext(): Context {
        return view!!.getContext()
    }


    fun getPullRequests(page: Int, restart: Boolean) {
        if (restart)
            pullRequestListData.clear()

        view?.showProgress(true)
        getGitHubPullRequestsUseCase.invoke(ownerName, repoName, page, this)

    }

    fun getPullRequests() {

        if (pullRequestListData.size == 0) {
            view?.showProgress(true)
            getGitHubPullRequestsUseCase.invoke(ownerName, repoName, 1, this)
        } else {
            view?.updateRepoLiveData(PullRequestModelMapper().apply(pullRequestListData))
            updateOpenClosedNumber()
            view?.showProgress(false)
        }
    }
}
