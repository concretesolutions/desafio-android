package app.kelvao.javapop.home

import app.kelvao.javapop.addTo
import app.kelvao.javapop.domain.repository.RepositoriesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private var view: HomeContract.IView?
) : HomeContract.IPresenter {

    private val disposable = CompositeDisposable()
    private var currentPage = 1

    override fun fetchRepositories() {
        showLoader()
        RepositoriesRepository
            .fetchRepositories(LANGUAGE, currentPage, SORT, LIMIT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentPage++
                if (currentPage == 1) {
                    view?.showRepositoriesResult(it.items)
                    view?.hideLargeLoader()
                } else {
                    view?.showMoreRepositoriesResult(it.items)
                    view?.hideListLoader()
                }
            }, {
                view?.notifyFetchRepositoriesError()
            })
            .addTo(disposable)
    }

    private fun showLoader() {
        if (currentPage == 1) {
            view?.showLargeLoader()
        } else {
            view?.showListLoader()
        }
    }

    override fun onDestroy() {
        view = null
        disposable.dispose()
    }

    companion object {
        private const val LANGUAGE: String = "Java"
        private const val SORT: String = "stars"
        private const val LIMIT: Int = 16
    }

}