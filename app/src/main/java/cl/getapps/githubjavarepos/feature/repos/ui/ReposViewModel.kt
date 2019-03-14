package cl.getapps.githubjavarepos.feature.repos.ui

import androidx.lifecycle.MutableLiveData
import cl.getapps.githubjavarepos.core.android.BaseViewModel
import cl.getapps.githubjavarepos.core.extension.DomainRepo
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.feature.repos.domain.GetRepos
import io.reactivex.disposables.Disposable

class ReposViewModel(private val getRepos: GetRepos) : BaseViewModel() {

    var repos: MutableLiveData<MutableList<DomainRepo>> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getReposs() = repos

    fun fetchRepos(params: ReposParams) {
        disposable = getRepos.execute(params)
            .subscribe({
                repos.postValue(it.toDomainRepos())
            }, {
                repos.postValue(mutableListOf())
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
