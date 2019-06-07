package br.com.renan.desafioandroid

import br.com.renan.desafioandroid.model.service.PullRequestService
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
        val requestDisposable: Disposable = pullRequestService.getData("ReactiveX", "RxJava")
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