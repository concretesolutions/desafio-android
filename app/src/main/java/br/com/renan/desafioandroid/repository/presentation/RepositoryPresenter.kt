package br.com.renan.desafioandroid.repository.presentation

import br.com.renan.desafioandroid.model.service.RepositoryService
import br.com.renan.desafioandroid.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoryPresenter : IRepositoryContract.Presenter {

    private lateinit var repositoryPresenter: RepositoryService
    private var compositeDisposable: CompositeDisposable? = null
    lateinit var view: IRepositoryContract.View

    override fun bind(view: IRepositoryContract.View) {
        this.view = view
    }

    override fun requestRepositoryData(userId: Int) {
        repositoryPresenter = ServiceProvides.getRepositoryService()

        val requestDisposable: Disposable = repositoryPresenter.getData("language:Java", "stars", 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.populateRepositorySuccess(it)
            },
                {
                    it.stackTrace
                })
        compositeDisposable?.add(requestDisposable)
    }
}