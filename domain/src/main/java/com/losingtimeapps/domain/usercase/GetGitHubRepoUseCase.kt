package com.losingtimeapps.domain.usercase

import android.util.Log
import android.widget.Toast
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.ParseError
import com.losingtimeapps.domain.boundary.BaseScheduler
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.boundary.ResponseBoundary
import com.losingtimeapps.domain.entity.Repository
import io.reactivex.Single

class GetGitHubRepoUseCase(
    private val gitHubRepository: GitHubRepository,
    private val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(language: String, sort: String, page: Int, responseBoundary: ResponseBoundary) {

        if (language.isEmpty()) {
            responseBoundary.onError(Error.EmptyLanguageError)
            return
        }
        gitHubRepository.getGitHubRepositories(language, sort, page)
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())
            .subscribe(
                { repositorys ->
                    responseBoundary.onGetRepository(repositorys)
                },
                { error ->
                    responseBoundary.onError(ParseError().parse(error))
                })


    }
}