package br.com.renan.desafioandroid.model.service

import br.com.renan.desafioandroid.model.data.PullRequestList
import br.com.renan.desafioandroid.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PullRequestService {

    fun getData(login: String, repoName: String): Flowable<PullRequestList> {
        return NetworkProvider.getApi()
            .getPullRequests(login, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}