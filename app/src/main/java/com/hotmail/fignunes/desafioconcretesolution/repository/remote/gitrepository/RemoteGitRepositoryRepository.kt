package com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.services.GitRepositoryServices
import com.hotmail.fignunes.skytestefabionunes.repository.remote.gitrepository.resources.RemoteGitRepositoryResources
import io.reactivex.Single
import retrofit2.Response

class RemoteGitRepositoryRepository(private val gitRepositoryServices: GitRepositoryServices) :
    RemoteGitRepositoryResources {
    override fun getGitRepositories(language: String, sort: String, page: Int): Single<Response<GitRepositoryResponses>> =
        gitRepositoryServices.getGitRepositories(language, sort, page)
}