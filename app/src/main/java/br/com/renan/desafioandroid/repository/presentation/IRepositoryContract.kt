package br.com.renan.desafioandroid.repository.presentation

import br.com.renan.desafioandroid.model.data.RepositoryItemsList

interface IRepositoryContract {

    interface View {
        fun repositorySuccess(repositoryList: RepositoryItemsList)
        fun showRepositoryLoading()
        fun showRepositoryError()
        fun showRepositorySucess()
    }

    interface Presenter {
        fun bind(view: View)
        fun requestRepositoryData(page: Int)
    }
}