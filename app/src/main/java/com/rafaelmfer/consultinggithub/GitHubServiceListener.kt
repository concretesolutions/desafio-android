package com.rafaelmfer.consultinggithub

import com.rafaelmfer.consultinggithub.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.model.repositories.GitRepositoriesResponse

interface GitHubServiceListener {

    fun onSuccess(response: GitRepositoriesResponse) {}
    fun onSuccess(response: List<GitPullRequestResponse>) {}
    fun onError(error: Throwable)
}