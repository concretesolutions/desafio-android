package br.com.briziapps.desafioconcrete.ui.repo_pulls

import android.content.Context
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects
import br.com.briziapps.desafioconcrete.mvp.Mvp

class PresenterRepoPulls(private val viewRepoPulls: Mvp.ViewRepoPulls) : Mvp.PresenterRepoPulls {

    private lateinit var context: Context
    private var modelRepoPulls: ModelRepoPulls = ModelRepoPulls(this)

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun getRepoPullsOnApi(repoName: String, page: String) {
        modelRepoPulls.getRepoPullsOnApi(repoName, page)
    }

    override fun updateRecyclerViewRepoPulls(repositoriePulls: List<GitHubRepoPullObjects>) {
        viewRepoPulls.updateRecyclerViewRepoPulls(repositoriePulls)
    }

    override fun hideProgressBar() {
        viewRepoPulls.hideProgressBar()
    }
}