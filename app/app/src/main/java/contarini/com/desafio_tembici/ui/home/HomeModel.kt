package contarini.com.desafio_tembici.ui.home

import contarini.com.desafio_tembici.Constants
import contarini.com.desafio_tembici.data.extensions.singleSubscribe
import contarini.com.desafio_tembici.data.repository.RepositoriesRepository
import io.reactivex.disposables.CompositeDisposable

class HomeModel(private val mPresenter : HomeContract.Presenter) : HomeContract.Model {

    private var mDisposable = CompositeDisposable()

    override fun loadIncidents(page : Int) {
        mDisposable.add(
            RepositoriesRepository.getIncidents(Constants.LANGUAGE_API, Constants.SORT_API, page).singleSubscribe(
                onSuccess = {
                    mPresenter.setIncidents(it)
                },

                onError = {
                    mPresenter.setError(it)
                }
            ))
    }
}