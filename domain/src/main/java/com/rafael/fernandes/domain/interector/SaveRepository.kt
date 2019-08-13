package com.rafael.fernandes.domain.interector

import com.rafael.fernandes.domain.PostExecutionThread
import com.rafael.fernandes.domain.executor.ThreadExecutor
import com.rafael.fernandes.domain.model.Item
import com.rafael.fernandes.domain.repositories.IOFFLineRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SaveRepository @Inject constructor(

    private val offLineRepository: IOFFLineRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<Long, Item>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: Item): Single<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buildUseCaseObservable(params: Item): Observable<Long> {
        return Observable.just(offLineRepository.save(params))
    }
}