package br.com.renan.desafioandroid.pullrequest.presentation

import br.com.renan.desafioandroid.model.service.PullRequestService
import br.com.renan.desafioandroid.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PullRequestPresenter : IPullRequestContract.Presenter {

    lateinit var pullRequestService: PullRequestService
    private var compositeDisposable: CompositeDisposable? = null
    lateinit var view: IPullRequestContract.View

    override fun bind(view: IPullRequestContract.View) {
        this.view = view
    }

    //criador = login
    //repositorio == name
    override fun requestPullRequestData(login: String, repoName: String) {
        pullRequestService = ServiceProvides.getPullRequestService()
        val requestDisposable: Disposable = pullRequestService.getData(login, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.pullRequestSuccess(it)
                view.showPullRequestSucess()
            },
                {
                    view.showPullRequestError()
                    it.stackTrace
                })
        compositeDisposable?.add(requestDisposable)
    }
}