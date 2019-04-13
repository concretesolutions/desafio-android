package com.dobler.desafio_android.ui.pull

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dobler.desafio_android.data.model.RepositoryPullRequest
import com.dobler.desafio_android.data.repository.pullRequest.PullRequestRepository
import com.dobler.desafio_android.util.rx.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import okio.Utf8
import java.net.URLEncoder

class PullRequestViewModel(
    private val repository: PullRequestRepository,
    private val schedulers: SchedulerContract
) : ViewModel() {

    val disposables = CompositeDisposable()
    var lastPageLoaded = 0
    val pullRequest = MutableLiveData<List<RepositoryPullRequest>>()

    var looaded: Boolean = false
    lateinit var user: String
    lateinit var repositoryName: String

    fun loadList() {

        Log.e("Chegou", lastPageLoaded.toString());
        if (!looaded) {

            val disposable = repository.getAll(user, repositoryName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    pullRequest.postValue(it)

                    looaded = true

                }, {
                    Log.e("TAG", it.toString())
                })

            disposables.add(disposable)

        }

    }
}
