package com.rafaelmfer.consultinggithub.mvvm.model

import com.rafaelmfer.consultinggithub.mvvm.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.mvvm.model.repositories.GitRepositoriesResponse

interface GitHubServiceListener {

    fun onSuccess(response: GitRepositoriesResponse) {}
    fun onSuccess(response: List<GitPullRequestResponse>) {}
    fun onError(error: Throwable)
}