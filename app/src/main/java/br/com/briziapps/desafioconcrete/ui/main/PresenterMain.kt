package br.com.briziapps.desafioconcrete.ui.main

import android.content.Context
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoObjects
import br.com.briziapps.desafioconcrete.mvp.Mvp

class PresenterMain(private val viewMain:Mvp.ViewMain) :Mvp.PresenterMain{

    private lateinit var context:Context
    private var modelMain: ModelMain = ModelMain(this)

    override fun getContext(context: Context) {
        this.context = context
    }

    override fun getReposOnApi(search: String, page: String) {
        modelMain.getReposOnApi(search, page)
    }

    override fun updateRecyclerViewRepos(repositories: List<GitHubRepoObjects>) {
        viewMain.updateRecyclerViewRepos(repositories)
    }

    override fun hideProgressBar() {
        viewMain.hideProgressBar()
    }
}