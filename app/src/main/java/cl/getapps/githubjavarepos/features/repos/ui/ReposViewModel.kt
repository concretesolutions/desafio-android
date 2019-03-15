package cl.getapps.githubjavarepos.features.repos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.domain.model.RepoModel
import cl.getapps.githubjavarepos.features.repos.domain.usecase.GetRepos
import io.reactivex.disposables.Disposable

class ReposViewModel(private val getRepos: GetRepos) : ViewModel() {

    var repos: MutableLiveData<MutableList<RepoModel>> = MutableLiveData()
    private var disposable: Disposable? = null

    fun getReposLiveData() = repos

    fun fetchRepos(params: ReposParams) {
        disposable = getRepos.execute(params)
            .subscribe({
                repos.postValue(it.toReposModel())
            }, {
                repos.postValue(mutableListOf())
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
