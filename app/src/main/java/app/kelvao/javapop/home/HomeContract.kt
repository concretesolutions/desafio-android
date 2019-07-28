package app.kelvao.javapop.home

import app.kelvao.javapop.domain.network.response.RepositoryResponse

interface HomeContract {
    interface IView {
        fun showRepositoriesResult(repositories: List<RepositoryResponse>)
        fun showMoreRepositoriesResult(repositories: List<RepositoryResponse>)
        fun notifyFetchRepositoriesError()
        fun showListLoader()
        fun hideListLoader()
        fun showLargeLoader()
        fun hideLargeLoader()
    }

    interface IPresenter {
        fun fetchRepositories()
        fun onDestroy()
    }
}