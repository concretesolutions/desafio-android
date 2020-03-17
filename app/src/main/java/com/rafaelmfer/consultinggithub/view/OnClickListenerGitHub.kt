package com.rafaelmfer.consultinggithub.view

interface OnClickListenerGitHub {

    fun onClickOpenWeb(htmlUrl: String) {}

    fun onClickOpenPullRequestsList(creator: String, repositoryId: String) {}
}