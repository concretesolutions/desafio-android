package com.losingtimeapps.presentation.ui.home.repository

import android.content.Context
import com.losingtimeapps.common.BaseViewModel
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.boundary.ResponseBoundary
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase
import com.losingtimeapps.presentation.mapper.RepositoryModelMapper
import com.losingtimeapps.presentation.model.RepositoryModel

class RepositoryViewModel(
    private val getGitHubRepoUseCase: GetGitHubRepoUseCase
) : BaseViewModel(), ResponseBoundary {

    private val repoListData = mutableListOf<Repository>()
    private var view: RepositoryView? = null

    fun setView(view: RepositoryView) {
        this.view = view
    }

    override fun onGetRepository(repoListData: List<Repository>) {
        this.repoListData.addAll(repoListData)
        view?.updateRepoLiveData(RepositoryModelMapper().apply(repoListData))
        view?.showProgress(false)
    }

    override fun onGetPullRequest(pullRequestListData: List<PullRequest>) {

    }

    override fun onError(error: Error) {
        view?.showSnackbarError(error)
        view?.showProgress(false)
    }

    fun onClickRepository(repositoryModel: RepositoryModel) {
        view?.navigateToPullRequestView(repositoryModel.authorModel.name, repositoryModel.name)
    }

    fun getContext(): Context {
        return view!!.getContext()
    }

    fun getRepositorys(page: Int, restart: Boolean) {
        if (restart)
            repoListData.clear()
        view?.showProgress(true)
        getGitHubRepoUseCase.invoke("language:Java", "stars", page, this)
    }

    fun getRepositorys() {

        if (repoListData.size == 0) {
            view?.showProgress(true)
            getGitHubRepoUseCase.invoke("language:Java", "stars", 1, this)
        } else {
            view?.updateRepoLiveData(RepositoryModelMapper().apply(repoListData))
            view?.showProgress(false)
        }
    }

}
