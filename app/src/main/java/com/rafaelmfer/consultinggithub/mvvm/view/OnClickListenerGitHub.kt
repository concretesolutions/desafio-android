package com.rafaelmfer.consultinggithub.mvvm.view

interface OnClickListenerGitHub {

    fun onClickOpenWeb(htmlUrl: String) {}

    fun onClickOpenPullRequestsList(creator: String, repositoryId: String) {}
}