package com.example.githubtest.features.pullrequest

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtest.data.model.PullRequest
import com.example.githubtest.data.model.ViewStateModel
import com.example.githubtest.data.request.RepositoryContract
import io.reactivex.disposables.CompositeDisposable

class PullRequestViewModel(private val repository: RepositoryContract) : ViewModel(), LifecycleObserver{

    val QUERY_STATUS = "all"
    private val disposables = CompositeDisposable()
    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<PullRequest>>> = MutableLiveData()

    fun getPullRequests() = viewStateResponse

    fun loadPullRequests(repositoryOwner: String = "", repositoryName: String = "", status: String = QUERY_STATUS) {
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(this.repository.getPullRequests(repositoryOwner, repositoryName, status).subscribe(
            { base ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base))
            }, { error ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = error))
            }
        ))
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}