package com.concrete.desafio_android.contract

import com.concrete.desafio_android.data.domain.Repository

interface RepositoriesContract {

    interface View {
        fun showList(repositories: ArrayList<Repository>)
        fun showErrorMessage(messageId: Int)
    }

    interface Presenter {
        fun getRepositories()
    }

    interface Callback {
        fun onSuccess(repositories: ArrayList<Repository>)
        fun onError(messageId: Int)
    }

    interface Api {
        fun listJavaRepositories(pageNumber: Int)
    }

}