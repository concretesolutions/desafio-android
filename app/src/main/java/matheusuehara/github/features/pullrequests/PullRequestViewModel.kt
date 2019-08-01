package matheusuehara.github.features.pullrequests

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.data.request.RepositoryContract
import matheusuehara.github.util.Constants.QUERY_STATUS

class PullRequestViewModel(private val repository: RepositoryContract) : ViewModel(), LifecycleObserver {

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