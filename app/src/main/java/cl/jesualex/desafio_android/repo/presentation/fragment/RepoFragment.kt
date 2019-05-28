package cl.jesualex.desafio_android.repo.presentation.fragment

import android.view.View
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.presentation.Fragment
import cl.jesualex.desafio_android.repo.presentation.contract.RepoContract
import cl.jesualex.desafio_android.repo.presentation.presenter.RepoPresenter

/**
 * Created by jesualex on 2019-05-28.
 */
class RepoFragment: Fragment(), RepoContract.View {
    private val presenter = RepoPresenter()

    override fun initView(view: View) {
        presenter.setView(this)
        presenter.updateJavaRepos(true)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_repo
    }
}