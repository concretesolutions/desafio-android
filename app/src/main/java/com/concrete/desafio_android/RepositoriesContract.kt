package com.concrete.desafio_android

import com.concrete.desafio_android.domain.Repository

interface RepositoriesContract {

    interface View{
        fun showList(repositories: ArrayList<Repository>)
        fun showFailureMessage(message: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter{
        fun getRepositories(pageNumber: Int)
    }

    interface Callback{
        fun onSucces(repositories: ArrayList<Repository>)
        fun onError()
        fun onFailure(message: String)
    }

}