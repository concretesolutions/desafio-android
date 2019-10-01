package contarini.com.desafio_android.ui.request

import contarini.com.desafio_android.data.extensions.singleSubscribe
import contarini.com.desafio_android.data.models.PullRequestResponse
import contarini.com.desafio_android.data.repository.PullRequestRepository
import io.reactivex.disposables.CompositeDisposable

class PullRequestModel(private val mPresenter : PullRequestContract.Presenter) : PullRequestContract.Model {

    private var mDisposable = CompositeDisposable()
    private var openCount : Int = 0
    private var closedCount : Int = 0

    override fun loadPullRequest(creator: String, repository: String) {
        mDisposable.add(
            PullRequestRepository.getPullRequests(creator, repository).singleSubscribe(
                onSuccess = {
                    getState(it)

                },
                onError = {
                    mPresenter.setError(it)
                }
            ))
    }

    private fun getState(it: List<PullRequestResponse>){
        it.forEach{ pullRequest ->
            if(pullRequest.state == "open"){
                openCount++
            } else {
                closedCount++
            }
        }
        mPresenter.setPullRequests(it, openCount , closedCount)
    }
}