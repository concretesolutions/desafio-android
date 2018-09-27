package br.com.desafio.concrete.view.repository

import br.com.desafio.concrete.base.BasePresenter
import br.com.desafio.concrete.base.BaseView
import br.com.desafio.concrete.model.Repository
import ru.alexbykov.nopaginate.callback.PaginateView

/**
 * Created by Malkes on 24/09/2018.
 */
interface RepositoryListContract {
    interface View : BaseView, PaginateView{
        fun onListItemClicked(repository: Repository)
        fun onRepositoriesLoaded(repositories: List<Repository>)
        fun onLoadMoreComplete(repositories: List<Repository>)
    }

    interface Presenter : BasePresenter<View>{
        fun fetchRepositories(query: String)
        fun refreshList()
        fun loadMore()
    }
}