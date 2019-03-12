package com.hako.githubapi.domain.usecases

import com.hako.githubapi.data.database.dao.RepositoryDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent

class DeleteRepositories(private val daoRepository: RepositoryDao) : KoinComponent {
    fun execute(): Observable<Unit> {
        return Observable.fromCallable { daoRepository.nukeDatabase() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
