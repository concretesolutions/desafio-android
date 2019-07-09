package app.kelvao.javapop.home

import app.kelvao.javapop.addTo
import app.kelvao.javapop.domain.repository.RepositoriesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private var view: HomeContract.IView?
) : HomeContract.IPresenter {

    private val disposable = CompositeDisposable()

    override fun onViewReady() {
        fetchRepositories(0)
    }

    override fun fetchRepositories(page: Int) {
        RepositoriesRepository
            .fetchRepositories(LANGUAGE, page, SORT, LIMIT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.notifyFetchRepositoriesSuccess()
            }, {
                view?.notifyFetchRepositoriesError()
            })
            .addTo(disposable)

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