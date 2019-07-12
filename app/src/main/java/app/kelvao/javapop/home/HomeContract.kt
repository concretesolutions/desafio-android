package app.kelvao.javapop.home

import app.kelvao.javapop.domain.network.response.RepositoryResponse

interface HomeContract {
    interface IView {
        fun showRepositoriesResult(repositories: List<RepositoryResponse>)
        fun notifyFetchRepositoriesSuccess()
        fun notifyFetchRepositoriesError()
    }

    interface IPresenter {
        fun onViewReady()
        fun fetchRepositories(page: Int)
        fun fetchUserInformation(login: String)
        fun onDestroy()
    }
}