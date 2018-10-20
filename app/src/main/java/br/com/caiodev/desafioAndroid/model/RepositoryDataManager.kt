package br.com.caiodev.desafioAndroid.model

import android.annotation.SuppressLint
import br.com.caiodev.desafioAndroid.factory.RepositoryServiceFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RepositoryDataManager {

    private val service: RepositoryCallInterface = RepositoryServiceFactory().createService()

    @SuppressLint("CheckResult")
    fun getRepoList(language: String,
                    sortBy: String,  page: Int, maxResults: Int): Single<RepositoryListModel> {
        return service.getRepoList(language,
            sortBy, page, maxResults)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError{
                Timber.e(it, this@RepositoryDataManager::class.java.canonicalName, it.localizedMessage)
            }
    }

    @SuppressLint("CheckResult")
    fun getOwnerData(ownerLogin: String): Single<RepositoryOwnerModel> {
        return service.getOwnerData(ownerLogin)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError{
                Timber.e(it, this@RepositoryDataManager::class.java.canonicalName, it.localizedMessage)
            }
    }
}