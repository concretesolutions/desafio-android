package br.com.rmso.popularrepositories.ui.repository

import br.com.rmso.popularrepositories.model.Repository

interface IRepository {

    interface View {
        fun progressBar(status: Boolean)
        fun updateList (list: List<Repository>?, lastPosition: Int)
        fun errorRequest (message: String)
    }

    interface Presenter{
        fun requestList(page: Int, lastPosition: Int)
    }
}