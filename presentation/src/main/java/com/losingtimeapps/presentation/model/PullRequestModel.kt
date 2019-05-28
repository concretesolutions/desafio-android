package com.losingtimeapps.presentation.model

data class PullRequestModel(
    val id: Long,
    val title: String,
    val data: String,
    val body: String,
    val authorModel: AuthorModel,
    val pullRequestUrl: String,
    val state: String
)