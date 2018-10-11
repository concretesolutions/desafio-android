package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable

class SaveRepoPageInteractor(private val repository: RepoRepository): InteractorFlowable<List<Long>,SaveRepoPageInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repository.getTotal()
                .map {it.div(request.limit).plus(1)}
                .toFlowable()
                .flatMap{repository.getList(it)}
                .flatMap { repository.save(it)}
    }


    data class Request(val limit:Int) : InteractorFlowable.Request()
}