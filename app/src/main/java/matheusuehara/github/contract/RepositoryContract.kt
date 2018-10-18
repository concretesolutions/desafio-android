package matheusuehara.github.contract

import matheusuehara.github.model.Repository

interface RepositoryContract {

    interface View {
        fun updateMovies(repositoryResult: List<Repository>)
        fun showProgressBar()
        fun hideProgressBar()
        fun showNetworkError()
        fun showEmptyMoviesMessage()
    }

    interface Presenter {
        fun getRepositorySuccess(repositoryResult: List<Repository>?)
        fun getMoviesError()
    }

}
