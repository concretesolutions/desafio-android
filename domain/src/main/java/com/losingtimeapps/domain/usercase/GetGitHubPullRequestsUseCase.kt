package com.losingtimeapps.domain.usercase

import android.util.Log
import android.widget.Toast
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.ParseError
import com.losingtimeapps.domain.boundary.BaseScheduler
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.boundary.ResponseBoundary
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class GetGitHubPullRequestsUseCase(
    val gitHubRepository: GitHubRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(ownerName: String, repositoryName: String, page: Int, responseBoundary: ResponseBoundary) {
        Int
        gitHubRepository.getGitHubPullRequests(ownerName, repositoryName, page)
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())
            .subscribe(
                { pullRequests ->
                    responseBoundary.onGetPullRequest(pullRequests)
                },
                { error ->
                    responseBoundary.onError(ParseError().parse(error))
                }
            )

    }
}