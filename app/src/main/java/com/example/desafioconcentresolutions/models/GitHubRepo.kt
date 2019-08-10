package com.example.desafioconcentresolutions.models

import com.squareup.moshi.Json
import java.net.URI


data class GitHubRepo(
    val name:String,
    val description:String,
    val forks:Int,
    @field:Json(name = "stargazers_count")val stars:Int,
    @field:Json(name = "full_name")val fullName:String,
    val owner:Owner
) {
}

data class Owner(
    @field:Json(name = "login")val username: String,
    val avatar_url:String
)