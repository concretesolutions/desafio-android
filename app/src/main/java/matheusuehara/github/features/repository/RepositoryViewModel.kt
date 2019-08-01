package matheusuehara.github.features.repository

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import matheusuehara.github.data.model.RepositoryResponse
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.data.request.RepositoryContract

class RepositoryViewModel(val repository: RepositoryContract) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val viewStateResponse: MutableLiveData<ViewStateModel<RepositoryResponse>> = MutableLiveData()

    companion object {
        private val language = "language:Java"
        private val sort = "stars"
        private var currentPage = 0
    }

    fun getRepositories() = viewStateResponse

    init {
        loadRepositories()
    }

    fun loadRepositories() {
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(this.repository.getRepositories(language, sort, currentPage).subscribe(
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