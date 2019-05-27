package com.losingtimeapps.presentationTest

import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.entity.Author
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.domainTest.MockBackendError
import io.reactivex.Single
import java.util.*

class GitHubRepositoryImpTest : GitHubRepository {

    var returnError: Boolean = false

    var returnEmptyList: Boolean = false

    override
    fun getGitHubRepositories(language: String, sort: String, page: Int): Single<List<Repository>> {
        return returnRepositoryData()
    }

    override fun getGitHubPullRequests(
        ownerName: String,
        repositoryName: String,
        page: Int
    ): Single<List<PullRequest>> {
        return returnPullRequestData()
    }


    private fun returnPullRequestData(): Single<List<PullRequest>> {
        if (returnError) {
            return MockBackendError<List<PullRequest>>().networkConnectionErrorResponse
        }

        if (!returnEmptyList) {
            return Single.just(
                Arrays.asList(
                    PullRequest(
                        0,
                        "", "", "",
                        Author(0, "", ""),
                        ""
                    )
                )
            )
        }
        return Single.just(Arrays.asList<PullRequest>())
    }

    private fun returnRepositoryData(): Single<List<Repository>> {
        if (returnError) {
            return MockBackendError<List<Repository>>().networkConnectionErrorResponse
        }

        if (!returnEmptyList) {
            return Single.just(
                Arrays.asList(
                    Repository(
                        0,
                        "", "", 0, 0,
                        Author(0, "", "")
                    )
                )
            )
        }
        return Single.just(Arrays.asList<Repository>())
    }

}