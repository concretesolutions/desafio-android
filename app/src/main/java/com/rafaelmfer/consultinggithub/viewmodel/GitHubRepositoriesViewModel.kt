package com.rafaelmfer.consultinggithub.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rafaelmfer.consultinggithub.GitHubRepository
import com.rafaelmfer.consultinggithub.GitHubServiceListener
import com.rafaelmfer.consultinggithub.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.model.repositories.GitRepositoriesResponse

class GitHubRepositoriesViewModel(
    private val repository: GitHubRepository
) : ViewModel(), GitHubServiceListener {

    sealed class Command {
        object ShowLoading : Command()
        object HideLoading : Command()
    }

    val command = MutableLiveData<Command>()
    val gitHubRepositories = MutableLiveData<GitRepositoriesResponse>()
    val gitHubPullRequestsList = MutableLiveData<List<GitPullRequestResponse>>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun getRepositoriesList(page: Int) {
        command.value = Command.ShowLoading
        repository.getRepositoriesList(this, page)
    }

    override fun onSuccess(response: GitRepositoriesResponse) {
        command.value = Command.HideLoading
        gitHubRepositories.value = response
    }

    override fun onError(error: Throwable) {
        command.value = Command.HideLoading
        errorLiveData.value = error
    }

    fun getPullRequestsList(creator: String, repository: String, page: Int) {
        command.value = Command.ShowLoading
        this.repository.getPullRequestsList(this, creator, repository, page)
    }

    override fun onSuccess(response: List<GitPullRequestResponse>) {
        command.value = Command.HideLoading
        gitHubPullRequestsList.value = response
    }

}