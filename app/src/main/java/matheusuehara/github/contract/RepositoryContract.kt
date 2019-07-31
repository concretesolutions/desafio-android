package matheusuehara.github.contract

import matheusuehara.github.model.Repository

interface RepositoryContract {

    interface View {
        fun updateRepositories(repositoryResult: List<Repository>)
        fun showProgressBar()
        fun hideProgressBar()
        fun showNetworkError()
        fun showEmptyRepositoryMessage()
    }

    interface Presenter {
        fun getRepositories()
        fun getRepositorySuccess(repositoryResult: List<Repository>?)
        fun getRepositoryError()
    }

}
