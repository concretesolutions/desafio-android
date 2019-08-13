package com.rafael.fernandes.domain.interector

import com.rafael.fernandes.domain.PostExecutionThread
import com.rafael.fernandes.domain.executor.ThreadExecutor
import com.rafael.fernandes.domain.model.GitPullRequestRequest
import com.rafael.fernandes.domain.model.GitPullRequests
import com.rafael.fernandes.domain.repositories.IRepositories
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetPullRequest @Inject constructor(

    private val iRepositories: IRepositories,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<GitPullRequests>, GitPullRequestRequest>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: GitPullRequestRequest): Observable<List<GitPullRequests>> {
        TODO("não se aplica")
    }

    override fun buildUseCaseSingle(params: GitPullRequestRequest): Single<List<GitPullRequests>> {
        return iRepositories.getPullRequest(params)
    }

}