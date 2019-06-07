package br.com.renan.desafioandroid.model.service

import br.com.renan.desafioandroid.model.data.RepositoryItemsList
import br.com.renan.desafioandroid.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryService {
    fun getData(language: String, sort: String, page: Int): Flowable<RepositoryItemsList> {
        return NetworkProvider.getApi()
            .getRepositories(language, sort, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}