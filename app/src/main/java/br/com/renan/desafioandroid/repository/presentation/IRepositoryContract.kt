package br.com.renan.desafioandroid.repository.presentation

import br.com.renan.desafioandroid.model.data.RepositoryItensList

interface IRepositoryContract {

    interface View {
        fun populateRepositorySuccess(repositoryList: RepositoryItensList)
    }

    interface Presenter {
        fun bind(view: View)
        fun requestRepositoryData(userId: Int)
    }
}