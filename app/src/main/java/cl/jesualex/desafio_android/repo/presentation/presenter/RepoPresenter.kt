package cl.jesualex.desafio_android.repo.presentation.presenter

import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.remote.UseCaseObserver
import cl.jesualex.desafio_android.repo.presentation.contract.RepoContract
import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import cl.jesualex.desafio_android.search.domain.use_case.SearchJavaRepoByStartUseCase

/**
 * Created by jesualex on 2019-05-28.
 */
class RepoPresenter: RepoContract.Presenter {
    private val searchJavaRepoUseCase = SearchJavaRepoByStartUseCase()
    private val firstPage = 1

    private lateinit var view: RepoContract.View

    private var pageIndex = firstPage + 1

    override fun setView(view: RepoContract.View) {
        this.view = view
    }

    override fun updateJavaRepos(removeOld: Boolean) {
        updateJavaRepos(firstPage, removeOld)
    }

    override fun loadNewJavaRepoPage() {
        updateJavaRepos(pageIndex, false)
    }

    private fun updateJavaRepos(page: Int, removeOld: Boolean){
        searchJavaRepoUseCase.execute(page, object : UseCaseObserver<SearchBase>(){
            override fun onNext(value: SearchBase) {
                super.onNext(value)
                if(page != firstPage){
                    pageIndex++
                }
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view.showErrorMessage(R.string.error_update)
            }
        })
    }
}