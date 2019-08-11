package com.example.desafioconcentresolutions.models

import com.squareup.moshi.Json

data class GitHubPull(
    val title:String,
    val html_url:String,
    val created_at:String,
    @field:Json(name="body")val description:String,
    val user:User
)

data class User(
    @field:Json(name = "login")val username: String,
    val avatar_url:String,
    @field:Json(name = "full_name")val fullName:String = "Mocked Full Name"
)