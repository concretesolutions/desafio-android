package com.concrete.desafio_android

import com.concrete.desafio_android.domain.Repository

class RepositoriesPresenter(
    private val view: RepositoriesContract.View
): RepositoriesContract.Presenter, RepositoriesContract.Callback{

    private val repository = GithubRepository(this)

    override fun getRepositories(pageNumber: Int) {
        repository.listJavaRepositories(pageNumber)
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