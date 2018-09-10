package com.concrete.desafioandroid.features.pulls

import android.util.Log
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.data.source.datasource.DataSource
import com.concrete.desafioandroid.utils.EMPTY_STRING
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class PullsInteractor(private val reposRepository: DataSource) {

    fun getPullsList(pullUrl: String,
                     onGetPullsListSuccess: (list: List<PullRequest>,
                                             openedIssues: Int,
                                             closedIssues: Int) -> Unit,
                     onError: (msg: String?) -> Unit): Disposable {
        return reposRepository.getPullsRequests(pullUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            var openedIssues = 0
                            var closedIssues = 0
                            for (pull in it) {
                                if ("open".equals(pull.state)) {
                                    openedIssues++
                                } else {
                                    closedIssues++
                                }
                            }

                            onGetPullsListSuccess(it, openedIssues, closedIssues)
                        },
                        {
                            it.printStackTrace()
                            onError(it.message ?: EMPTY_STRING)
                        },
                        { Log.d("ONCOMPLETE-PULLS", "OnComplete-Pulls")}
                )
    }

}