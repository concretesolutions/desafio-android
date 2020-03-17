package com.marraps.mvvmshow.numberlist.model

import com.rafaelmfer.consultinggithub.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.model.repositories.GitRepositoriesResponse

interface GitHubServiceListener {

    fun onSuccess(response: GitRepositoriesResponse) {}
    fun onSuccess(response: GitPullRequestResponse) {}
    fun onError(error: Throwable)
}