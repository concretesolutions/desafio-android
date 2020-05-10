package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions

import com.hotmail.fignunes.desafioconcretesolution.common.exceptions.EmptyReturnException
import com.hotmail.fignunes.desafioconcretesolution.repository.Repository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.gitrepository.responses.GitRepositoryResponses
import io.reactivex.Single
import retrofit2.HttpException

class GetGitRepositories(private val repository: Repository) {

    fun execute(language: String, sort: String, page: Int): Single<GitRepositoryResponses> {
        return repository.remote.gitRepository.getGitRepositories(language, sort, page)
            .map {
                when (it.code()) {
                    200 -> it?.body() ?: throw EmptyReturnException()
                    else -> throw HttpException(it)
                }
            }
    }
}