package matheusuehara.github.features.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import matheusuehara.github.data.model.RepositoryResponse
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.data.request.RepositoryContract
import matheusuehara.github.util.Constants.QUERY_LANGUAGE
import matheusuehara.github.util.Constants.QUERY_SORT

class RepositoryViewModel(val repository: RepositoryContract) : ViewModel(), LifecycleObserver {

    private var currentPage = 1
    private val disposables = CompositeDisposable()
    private val viewStateResponse: MutableLiveData<ViewStateModel<RepositoryResponse>> = MutableLiveData()

    fun getRepositories() = viewStateResponse

    init {
        loadRepositories()
    }

    fun loadRepositories(language: String = QUERY_LANGUAGE, sort: String = QUERY_SORT, page: Int = currentPage) {
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