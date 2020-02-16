package com.concrete.desafio_android

import com.concrete.desafio_android.domain.Repository

class RepositoriesPresenter(
    private val view: RepositoriesContract.View
): RepositoriesContract.Presenter, RepositoriesContract.Callback{

    companion object {
        private var pageNumber = 1
    }

    private val repository = GithubRepository(repositoriesCallback = this)

    override fun getRepositories() {
        repository.listJavaRepositories(pageNumber)
        pageNumber += 1
    }

    override fun onSucces(repositories: ArrayList<Repository>) {
        view.showList(repositories)

    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        view.showFailureMessage(message)
    }
}