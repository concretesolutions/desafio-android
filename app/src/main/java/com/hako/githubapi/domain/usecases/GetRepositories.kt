package com.hako.githubapi.domain.usecases

import com.hako.githubapi.data.database.dao.RepositoryDao
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class GetRepositories(private val daoRepository: RepositoryDao) : KoinComponent {

    private val api: RemoteDatasource = get()

    fun execute(index: Int): Observable<List<Repository>> {
        return Observable.fromCallable { daoRepository.getPage(index) }
            .concatMap { dbRepository ->
                // This is simple logic is good enough for the ocation, but a more desireable approach
                // would be to use some kind of paging.
                if (dbRepository.isEmpty() || daoRepository.count(index) == 0)
                    api.getRepositories(QueryRepository(page = index)).concatMap { repoList ->
                        daoRepository.saveAll(repoList)
                        Observable.just(repoList)
                    } else {
                    Observable.just(dbRepository)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
