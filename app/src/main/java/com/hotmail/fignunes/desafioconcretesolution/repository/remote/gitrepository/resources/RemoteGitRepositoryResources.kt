package com.hotmail.fignunes.skytestefabionunes.repository.remote.gitrepository.resources

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import io.reactivex.Single
import retrofit2.Response

interface RemoteGitRepositoryResources {
    fun getGitRepositories(language: String, sort: String, page: Int): Single<Response<GitRepositoryResponses>>
}