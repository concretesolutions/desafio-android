package com.diegoferreiracaetano.data.repo

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.api.GithubApi
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Retrofit

class RepoImpRepository(private var dao: RepoDao,
                        private val retrofit: Retrofit) : RepoRepository {

    private val api = retrofit.create(GithubApi::class.java)

    override fun getList(page: Int): Flowable<List<Repo>> {
        return api.getListRepo(page = page).map { it.items }
                .flatMap { Flowable.fromIterable(it)}
                .toList()
                .toFlowable()

    }

    override fun getList(): DataSource.Factory<Int, Repo> {
       return dao.getAll()
    }

    override fun save(list: List<Repo>): Flowable<List<Long>> {
       return Flowable.just(dao.save(list))
    }

    override fun getTotal(): Single<Int> {
       return dao.getTotal()
    }

}
