package br.com.renan.desafioandroid.model.data

import com.squareup.moshi.Json

data class PullRequestList (
    val pullRequestList: List<PullRequest>
)

data class PullRequest (
    @field:Json(name = "title") val title: String,
    @field:Json(name = "created_at") val createDate: String,
    @field:Json(name = "body") val body: String,
    @field:Json(name = "user") val user: User
)

data class User (
    @field:Json(name = "login") val user: String,
    @field:Json(name = "avatar_url") val avatarUrl: String
)