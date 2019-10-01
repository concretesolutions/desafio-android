package contarini.com.desafio_android.ui.home

import contarini.com.desafio_android.Constants
import contarini.com.desafio_android.data.extensions.singleSubscribe
import contarini.com.desafio_android.data.repository.RepositoriesRepository
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