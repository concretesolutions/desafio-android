package br.com.andreyneto.desafioandroid.ui.pulls

import android.content.Context
import br.com.andreyneto.desafioandroid.model.Pull
import br.com.andreyneto.desafioandroid.model.Repo
import br.com.andreyneto.desafioandroid.ui.base.BasePresenter
import br.com.andreyneto.desafioandroid.ui.base.BaseView

class PullsContract {
    interface View: BaseView<Presenter> {
        fun showData(items: List<Pull>)
    }

    interface Presenter: BasePresenter {
        fun getData(owner: String, repo: String)
        fun openPull(url: String, ctx: Context)
    }
}