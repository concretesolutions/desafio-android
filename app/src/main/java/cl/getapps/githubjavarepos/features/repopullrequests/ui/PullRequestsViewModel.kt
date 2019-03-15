package cl.getapps.githubjavarepos.features.repopullrequests.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.toPullRequestsModel
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.PullRequestModel
import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.User
import cl.getapps.githubjavarepos.features.repopullrequests.domain.usecase.GetPullRequests
import io.reactivex.disposables.Disposable

class PullRequestsViewModel(private val getPullRequests: GetPullRequests) : ViewModel() {

    var pullRequests: MutableLiveData<MutableList<PullRequestModel>> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getPullRequestsLiveData() = pullRequests

    fun fetchPullRequests(params: PullRequestParams) {
        disposable = getPullRequests.execute(params)
            .subscribe({
                pullRequests.postValue(it.toPullRequestsModel())
            }, {
                pullRequests.postValue(mutableListOf(PullRequestModel(User("Error", "Error"),"Error", "Error", "Error")))
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
