package com.concrete.desafio_android.presenter

import com.concrete.desafio_android.contract.RepositoriesContract
import com.concrete.desafio_android.data.external.GithubRepository
import com.concrete.desafio_android.data.domain.Repository

class RepositoriesPresenter(
    private val view: RepositoriesContract.View
) : RepositoriesContract.Presenter,
    RepositoriesContract.Callback {

    companion object {
        private var pageNumber = 1
    }

    private val repository: RepositoriesContract.Api =
        GithubRepository(
            repositoriesCallback = this
        )

    override fun getRepositories() {
        repository.listJavaRepositories(pageNumber)
        pageNumber += 1
    }

    override fun onSuccess(repositories: ArrayList<Repository>) {
        view.showList(repositories)

    }

    override fun onError(messageId: Int) {
        view.showErrorMessage(messageId)
    }

}