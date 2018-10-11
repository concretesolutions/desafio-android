package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Flowable

class SavePullPageInteractor(private val repository: PullRepository): InteractorFlowable<List<Long>,SavePullPageInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repository.getTotal()
                .map {it.div(request.limit).plus(1)}
                .toFlowable()
                .flatMap{repository.getList(request.owner,request.repo,it)}
                .flatMap{ repository.save(it)}
    }


    data class Request(val owner: String,val repo:String,val limit:Int) : InteractorFlowable.Request()
}