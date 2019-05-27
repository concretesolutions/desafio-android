package com.losingtimeapps.domain.entity

data class PullRequest(
    val id: Long,
    val title: String,
    val data: String,
    val body: String,
    val author: Author,
    val pullRequestUrl:String
)