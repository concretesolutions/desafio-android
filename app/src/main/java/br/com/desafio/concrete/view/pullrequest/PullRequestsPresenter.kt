package br.com.desafio.concrete.view.pullrequest

import br.com.desafio.concrete.base.ChallengeBasePresenter
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.network.ApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Malkes on 24/09/2018.
 */
class PullRequestsPresenter(private val api: ApiDataSource) :
        ChallengeBasePresenter<PullRequestContract.View>(),
        PullRequestContract.Presenter {

    override fun fetchPullRequests(repository: Repository) {
        view?.let { view ->

            view.showLoadingIndicator(true)

            api.fetchPullRequests(repository)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate{
                        view.showLoadingIndicator(false)
                    }
                    .subscribe({ response ->
                        if(response.isNotEmpty()){
                            view.onPullRequestLoaded(response)
                        }else{
                            view.showEmptyState()
                        }
                    }, { throwable ->
                        view.showUnexpectedError(throwable)
                    })
        }
    }


}