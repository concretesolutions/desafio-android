package cl.mauledev.github.view.viewmodels

import android.arch.lifecycle.*
import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.data.repositories.RepoRepository
import cl.mauledev.github.di.app.GithubApp
import cl.mauledev.github.di.modules.RepoModule
import cl.mauledev.github.utils.ConnectionState
import cl.mauledev.github.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainViewModel: ViewModel(), LifecycleObserver {

    @Inject
    lateinit var repository: RepoRepository

    private val list = ArrayList<Repo>()

    private var repos: MutableLiveData<List<Repo>> = MutableLiveData()

    private var pullRequests: MutableLiveData<List<PullRequest>> = MutableLiveData()

    private var isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private var state: SingleLiveEvent<ConnectionState> = SingleLiveEvent()

    private var selectedRepo: SingleLiveEvent<Repo> = SingleLiveEvent()

    private var selectedPullRequest: SingleLiveEvent<PullRequest> = SingleLiveEvent()

    init {
        initDagger()
    }

    private fun initDagger() {
        GithubApp.generalComponent.plus(RepoModule(this)).inject(this)
    }

    fun checkRepos(): LiveData<List<Repo>>? {
        return repos
    }

    fun checkPullRequests(): MutableLiveData<List<PullRequest>> {
        return pullRequests
    }

    fun clearPullRequests() {
        pullRequests.postValue(null)
    }

    fun checkIsLoading(): SingleLiveEvent<Boolean> {
        return isLoading
    }

    fun checkConnectedState(): SingleLiveEvent<ConnectionState> {
        return state
    }

    fun setConnectedState(stateValue: ConnectionState) {
        state.postValue(stateValue)
    }

    fun getRepos(page: Int, isConnected: Boolean) {

        if (!isConnected) {
            state.postValue(ConnectionState.DISCONNECTED)
            isLoading.postValue(false)
            return
        }

        if (page <= 1) {
            list.clear()
        }

        state.postValue(ConnectionState.CONNECTED)
        isLoading.postValue(true)

        repository.getRepos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.postValue(false)
                    list.addAll(it.items)
                    repos.postValue(list)
                }, {
                    it.printStackTrace()
                    isLoading.postValue(false)
                }, {
                    Timber.d("Completed!")
                })
    }

    fun getPullRequests(selectedRepo: Repo, isConnected: Boolean) {

        if (!isConnected) {
            state.postValue(ConnectionState.DISCONNECTED)
            isLoading.postValue(false)
            return
        }

        state.postValue(ConnectionState.CONNECTED)
        isLoading.postValue(true)

        repository.getPullRequests(selectedRepo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Saved ${it.size} elements")
                    isLoading.postValue(false)
                    pullRequests.postValue(it)
                }, {
                    it.printStackTrace()
                    isLoading.postValue(false)
                }, {
                    Timber.d("Completed!")
                })
    }

    fun getSelectedRepo(): SingleLiveEvent<Repo> {
        return selectedRepo
    }

    fun getSelectedPullRequest(): SingleLiveEvent<PullRequest> {
        return selectedPullRequest
    }

    fun setSelectedRepo(repo: Repo) {
        selectedRepo.postValue(repo)
    }

    fun setSelectedPullRequest(pullRequest: PullRequest) {
        selectedPullRequest.postValue(pullRequest)
    }
}