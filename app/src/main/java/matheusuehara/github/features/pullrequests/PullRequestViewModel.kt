package matheusuehara.github.features.pullrequests

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.data.request.RepositoryContract

class PullRequestViewModel(private val repository: RepositoryContract) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<PullRequest>>> = MutableLiveData()

    companion object {
        const val STATUS: String = "all"
    }

    fun getPullRequests() = viewStateResponse

    fun loadPullRequests(repositoryOwner: String = "", repositoryName: String = "") {
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(this.repository.getPullRequests(repositoryOwner, repositoryName, STATUS).subscribe(
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