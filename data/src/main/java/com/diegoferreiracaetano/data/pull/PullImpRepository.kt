package com.diegoferreiracaetano.data.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.api.GithubApi
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.Retrofit

class PullImpRepository(private var dao: PullDao,
                        private val retrofit: Retrofit) : PullRepository {

    private val api = retrofit.create(GithubApi::class.java)

    override fun getList(owner:String,repo:String,page : Int): Flowable<List<Pull>> {
        return api.getPull(owner,repo,page)
                .flatMap{Flowable.fromIterable(it)}
                .flatMapMaybe{Maybe.just(it.copy(ownerName = owner,repoName = repo))}
                .toList()
                .toFlowable()
    }

    override fun getList(owner:String,repo:String): DataSource.Factory<Int, Pull> {
        return dao.getAll(owner,repo)
    }

    override fun save(list: List<Pull>): Flowable<List<Long>> {
        return Flowable.just(dao.save(list))
    }

    override fun getTotal(): Single<Int> {
        return dao.getTotal()
    }
}
