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
    private var open: Int = 0
    private var close: Int = 0

    override fun bind(view: IPullRequestContract.View) {
        this.view = view
    }

    override fun requestPullRequestData(login: String, repoName: String) {
        view.showPullRequestLoading()
        pullRequestService = ServiceProvides.getPullRequestService()
        val requestDisposable: Disposable = pullRequestService.getData(login, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNullOrEmpty()) {
                    view.showPullRequestEmpty()
                } else {
                    view.pullRequestSuccess(it)
                    view.showPullRequestSucess()
                    view.showTotalPulls(it)
                }
            },
                {
                    view.showPullRequestError()
                    it.stackTrace
                })
        compositeDisposable?.add(requestDisposable)
    }


}