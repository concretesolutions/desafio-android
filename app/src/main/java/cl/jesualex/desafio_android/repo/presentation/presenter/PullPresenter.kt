package cl.jesualex.desafio_android.repo.presentation.presenter

import cl.jesualex.desafio_android.base.remote.UseCaseObserver
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import cl.jesualex.desafio_android.repo.data.domain.use_case.SearchPullsUseCase
import cl.jesualex.desafio_android.repo.presentation.contract.PullContract

/**
 * Created by jesualex on 2019-05-30.
 */
class PullPresenter: PullContract.Presenter {
    private val searchPullsUseCase = SearchPullsUseCase()

    private var view:PullContract.View? = null

    override fun updatePulls(name: String) {
        searchPullsUseCase.execute(name, object: UseCaseObserver<List<Pull>>(){
            override fun onNext(value: List<Pull>) {
                super.onNext(value)
                view?.setPulls(value)
                getTotals(value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view?.setPulls(null)
            }
        })
    }

    override fun setView(view: PullContract.View) {
        this.view = view
    }

    private fun getTotals(pulls: List<Pull>){
        view?.let {
            var opened = 0
            var closed = 0

            for(pull in pulls){
                when(pull.state){
                    "open" -> opened++
                    "closed" -> closed++
                }
            }

            it.setTotals(opened, closed)
        }
    }
}