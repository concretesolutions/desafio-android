package br.com.guilherme.solution.ui.repositoryList

import br.com.guilherme.solution.models.Response
import br.com.guilherme.solution.ui.base.Base

class RepositoryListContract {

    interface View : Base.View {
        fun showErrorMessage(error: String)
        fun loadDataSuccess(response: Response)
    }

    interface Presenter : Base.Presenter<View> {
        fun loadData(q: String, sort: String, page: Int)
    }
}