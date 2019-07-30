package wilquer.lima.desafioconcrete.ui.repository

import wilquer.lima.desafioconcrete.data.model.Repository

class RepositoryContract {

    interface View {
        fun setProgress(active: Boolean)

        fun initView()

        fun repositories(listRepositories: List<Repository>?)

        fun error(msg: String)
    }

    interface Presenter {
        fun getRepositories(pageNumber: Int)
    }
}