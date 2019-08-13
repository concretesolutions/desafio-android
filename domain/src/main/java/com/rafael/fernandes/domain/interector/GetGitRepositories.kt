package com.rafael.fernandes.domain.interector

import com.rafael.fernandes.domain.PostExecutionThread
import com.rafael.fernandes.domain.executor.ThreadExecutor
import com.rafael.fernandes.domain.model.GitRepositories
import com.rafael.fernandes.domain.model.GitRepositoryRequest
import com.rafael.fernandes.domain.repositories.IRepositories
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetGitRepositories @Inject constructor(

    private val iRepositories: IRepositories,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<GitRepositories, GitRepositoryRequest>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: GitRepositoryRequest): Observable<GitRepositories> {
        TODO("não se aplica")
    }

    override fun buildUseCaseSingle(params: GitRepositoryRequest): Single<GitRepositories> {
        return iRepositories.getOnlineRepositories(params)
    }

}