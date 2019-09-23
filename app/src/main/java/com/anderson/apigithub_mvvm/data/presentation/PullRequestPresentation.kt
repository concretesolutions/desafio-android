package com.anderson.apigithub_mvvm.data.presentation

/**
 * Created by anderson on 22/09/19.
 */
data class PullRequestPresentation(
    var loginUser: String,
    var avatarUrlUser: String,
    var title: String,
    var data: String,
    var body: String,
    var htmlUrl: String
)