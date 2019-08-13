package com.rafael.fernandes.domain.interector

import com.rafael.fernandes.domain.PostExecutionThread
import com.rafael.fernandes.domain.executor.ThreadExecutor
import com.rafael.fernandes.domain.model.GitRepositoryRequest
import com.rafael.fernandes.domain.model.Item
import com.rafael.fernandes.domain.repositories.IOFFLineRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetRepositories @Inject constructor(
    private val iOFFLineRepository: IOFFLineRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<MutableList<Item>, Void?>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Void?): Observable<MutableList<Item>> {
        return iOFFLineRepository.retrieveAllRepositoriesSaved()
    }

    override fun buildUseCaseSingle(params: Void?): Single<MutableList<Item>> {
        TODO("não se aplica")
    }

}