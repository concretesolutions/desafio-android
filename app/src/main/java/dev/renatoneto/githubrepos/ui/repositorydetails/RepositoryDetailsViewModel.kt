package dev.renatoneto.githubrepos.ui.repositorydetails

import androidx.lifecycle.MutableLiveData
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseViewModel
import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.network.github.GithubDataSource
import dev.renatoneto.githubrepos.util.rx.SchedulerContract

class RepositoryDetailsViewModel(
    private val repository: GithubDataSource,
    val githubRepository: GithubRepository, private val schedulers: SchedulerContract
) : BaseViewModel() {

    var contentLoaded = false

    val pullRequests = MutableLiveData<ArrayList<GithubPullRequest>>()

    val pullRequestsList = ArrayList<GithubPullRequest>()

    fun loadPullRequests() {

        if (!contentLoaded) {

            contentLoaded = true
            error.postValue(null)

            loading.postValue(true)

            val disposable = repository.getPullRequestsList(
                githubRepository.owner.login,
                githubRepository.name
            )
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({

                    loading.postValue(false)

                    pullRequestsList.addAll(it)
                    pullRequests.postValue(pullRequestsList)

                    if (pullRequestsList.size == 0) {
                        error.postValue(R.string.error_no_pull_requests)
                    }

                }, {
                    loading.postValue(false)
                    showError(it)
                })

            disposables.add(disposable)

        }

    }

}
