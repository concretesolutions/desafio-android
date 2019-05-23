package com.uharris.desafio_android.factory

import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.domain.models.User

object ResponseFactory {

    fun makeRepositoryResponse(): Repository {
        return Repository(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            makeUserResponse(), DataFactory.randomInt(), DataFactory.randomInt())
    }

    fun makeUserResponse(): User {
        return User(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun makePullRequestResponse(): PullRequest {
        return PullRequest(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            makeUserResponse(), DataFactory.randomString())
    }

    fun makeRepositoryList(): List<Repository> {
        val repositoryList = mutableListOf<Repository>()
        repositoryList.add(makeRepositoryResponse())
        return repositoryList
    }

    fun makeullRequestList(): List<PullRequest> {
        val pullRequestList = mutableListOf<PullRequest>()
        pullRequestList.add(makePullRequestResponse())
        return pullRequestList
    }
}