package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository

import android.view.View
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.exceptions.EmptyReturnException
import com.hotmail.fignunes.desafioconcretesolution.presentation.common.CheckIsOnline
import com.hotmail.fignunes.desafioconcretesolution.presentation.common.StringHelper
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions.GetGitRepositories
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions.ResponsesToGitRepository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.genericresponseserror.GenericResponsesErrorCodes
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import com.hotmail.fignunes.desafioconretesolution.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class GitRepositoryPresenter(
    private val contract: GitRepositoryContract,
    private val checkIsOnline: CheckIsOnline,
    private val getGitRepositories: GetGitRepositories,
    private val responsesToGitRepository: ResponsesToGitRepository,
    private val stringHelper: StringHelper
) : BasePresenter() {

    var showLoading: Int = View.GONE
        set(value) {
            field = value
            notifyChange()
        }

    fun onCreate(page: Int) {
        contract.initializeSwipe()
        contract.initializeScroolListener()
        searchGitRepository(page)
    }

    fun searchGitRepository(page: Int) {
        if (!checkIsOnline.execute()) {
            contract.message(R.string.without_internet_connection)
            contract.swipeOff()
            return
        }

        showLoading = View.VISIBLE

        getGitRepositories.execute(stringHelper.getString(R.string.language), stringHelper.getString(R.string.stars), page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                treatReturn(it)
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

    private fun treatReturn(responses: GitRepositoryResponses) {
        showLoading = View.GONE
        val gitRepository = responsesToGitRepository.execute(responses)
        contract.initializeList(gitRepository)
        contract.initializeAdapter(gitRepository)
        contract.swipeOff()
        contract.setFinishLoading()
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