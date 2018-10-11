package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Flowable

class SavePullInicialInteractor(private val repository: PullRepository): InteractorFlowable<List<Long>,SavePullInicialInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repository.getList(request.owner,request.repo,request.page)
                .flatMap{ repository.save(it) }
    }


    data class Request(val owner: String,val repo:String,val page: Int) : InteractorFlowable.Request()
}