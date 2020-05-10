package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest

import android.view.View
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.exceptions.EmptyReturnException
import com.hotmail.fignunes.desafioconcretesolution.model.PullRequest
import com.hotmail.fignunes.desafioconcretesolution.presentation.common.CheckIsOnline
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions.GetPullRequest
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions.ResponsesToPullRequest
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.genericresponseserror.GenericResponsesErrorCodes
import com.hotmail.fignunes.desafioconretesolution.common.BasePresenter
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class PullRequestPresenter(
    private val contract: PullRequestContract,
    private val getPullRequest: GetPullRequest,
    private val responsesToPullRequest: ResponsesToPullRequest,
    private val checkIsOnline: CheckIsOnline
) : BasePresenter() {

    private var pullRequests = listOf<PullRequest>()
    private var login: String = ""
    private var repositoryPr: String = ""

    var showLoading: Int = View.GONE
        set(value) {
            field = value
            notifyChange()
        }

    var opened: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var closed: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    fun onCreate(gitRepositoryItem: GitRepositoryItem?) {
        this.login = gitRepositoryItem!!.owner.login
        this.repositoryPr = gitRepositoryItem!!.name
        contract.initializeSwipe()
        searchPullRequest()
    }

    fun searchPullRequest() {
        if (!checkIsOnline.execute()) {
            contract.message(R.string.without_internet_connection)
            contract.swipeOff()
            return
        }

        showLoading = View.VISIBLE

        getPullRequest.execute(login, repositoryPr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showLoading = View.GONE
                pullRequests = responsesToPullRequest.execute(it)
                contract.initializeAdapter(pullRequests)
                message(pullRequests)
                contract.swipeOff()
            }, {
                showLoading = View.GONE
                contract.swipeOff()
                when (it) {
                    is EmptyReturnException -> R.string.return_empty
                    is HttpException -> httpExceptionHanding(it.code())
                    else -> R.string.erro_get_movie
                }.apply { contract.message(this) }

            })
            .also { addDisposable(it) }
    }

    fun message(responses: List<PullRequest>) {
        val open = responses.count {
            it.state.toLowerCase() == "open" }
        val close = responses.count {
            it.state.toLowerCase() == "close" }

        opened = "$open opened"
        closed = " / $close closed"
    }

    private fun httpExceptionHanding(code: Int) =
        when (code) {

            GenericResponsesErrorCodes.LIMIT_EXCEEDED.code -> R.string.limite_exceeded
            GenericResponsesErrorCodes.LIMIT_SEARCH_EXCEEDED.code -> R.string.limite_search_exceeded
            GenericResponsesErrorCodes.UNAVAILABLE_SERVICE.code -> R.string.unavailable_service
            GenericResponsesErrorCodes.ERROR_UNEXPECTED.code -> R.string.error_unexpected
            else -> R.string.service_unhandled_error
        }
}