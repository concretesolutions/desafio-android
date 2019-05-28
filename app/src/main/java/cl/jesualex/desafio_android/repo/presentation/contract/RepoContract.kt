package cl.jesualex.desafio_android.repo.presentation.contract

import cl.jesualex.desafio_android.base.presentation.PresenterBase
import cl.jesualex.desafio_android.base.presentation.ViewBase

/**
 * Created by jesualex on 2019-05-28.
 */
interface RepoContract {
    interface View: ViewBase

    interface Presenter: PresenterBase<View>{
        fun updateJavaRepos(removeOld: Boolean = false)
        fun loadNewJavaRepoPage()
    }
}