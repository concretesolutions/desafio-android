package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import br.com.cavreti.desafio_android.applicationCore.base.BasePresenter
import br.com.cavreti.desafio_android.data.PullRequest
import br.com.cavreti.desafio_android.network.RequestCallback
import retrofit2.Retrofit
import javax.inject.Inject
import br.com.cavreti.desafio_android.network.createRequest
import br.com.cavreti.desafio_android.network.service.PullRequestService

class PullRequestsPresenter @Inject constructor(private val retrofit: Retrofit, private val view : PullRequestsContract.View)
    : BasePresenter(), PullRequestsContract.Presenter{

    override fun getPullRequests(ownerName: String, repoName: String, page : Int) {
        createRequest(retrofit.create(PullRequestService::class.java).getPullRequests(ownerName, repoName, page), object : RequestCallback {
            override fun onError(throwable: Throwable) {
                view.loadPullRequestsFailed()
            }

            override fun onSuccess(response: Any?) {
                val pullRequests = response as List<PullRequest>
                view.loadPullRequests(pullRequests)
            }
        })
    }
}