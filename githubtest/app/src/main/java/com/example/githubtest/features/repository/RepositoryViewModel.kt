package com.example.githubtest.features.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtest.data.model.RepositoryResponse
import com.example.githubtest.data.model.ViewStateModel
import com.example.githubtest.data.request.RepositoryContract
import io.reactivex.disposables.CompositeDisposable

class RepositoryViewModel(val repository: RepositoryContract) : ViewModel(), LifecycleObserver {

    private var currentPage = 1
     val QUERY_LANGUAGE = "language:Kotlin"
     val QUERY_SORT = "stars"
    private val disposables = CompositeDisposable()
    private val viewStateResponse: MutableLiveData<ViewStateModel<RepositoryResponse>> = MutableLiveData()

    fun getRepository() = viewStateResponse

    init {
        loadRepository()
    }
     fun loadRepository(language: String = QUERY_LANGUAGE, sort: String = QUERY_SORT, page: Int = currentPage) {
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(this.repository.getRepositories(language, sort, page).subscribe(
            { base ->
                currentPage++
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