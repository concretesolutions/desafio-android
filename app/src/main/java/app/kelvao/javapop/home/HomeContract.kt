package app.kelvao.javapop.home

import app.kelvao.javapop.domain.network.response.RepositoryResponse

interface HomeContract {
    interface IView {
        fun showRepositoriesResult(repositories: List<RepositoryResponse>)
        fun notifyFetchRepositoriesSuccess()
        fun notifyFetchRepositoriesError()
        fun showMoreRepositoriesResult(items: List<RepositoryResponse>)
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