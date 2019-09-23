package br.com.cavreti.desafio_android.useCase.home

import br.com.cavreti.desafio_android.data.Repository

interface HomeContract {
    interface View {
        fun loadRepositories(repositories: List<Repository>)
        fun loadRepositoriesFailed()
    }

    interface Presenter {
        fun getRepositories(page : Int)
    }
}