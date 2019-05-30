package cl.jesualex.desafio_android.repo.presentation.contract

import android.support.v4.app.Fragment
import cl.jesualex.desafio_android.base.presentation.PresenterBase
import cl.jesualex.desafio_android.base.presentation.ViewBase
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import cl.jesualex.desafio_android.repo.data.local.view_model.RepoViewModel

/**
 * Created by jesualex on 2019-05-28.
 */
interface RepoContract {
    interface View: ViewBase{
        fun updateRepos(repos: List<Repo>)
    }

    interface Presenter: PresenterBase<View>{
        fun updateJavaRepos(removeOld: Boolean = false)
        fun loadNewJavaRepoPage()
        fun subscribeRepoViewModel(fragment: Fragment)
        fun unsubscribeRepoViewModel(fragment: Fragment)
    }
}