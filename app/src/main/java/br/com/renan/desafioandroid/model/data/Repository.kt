package br.com.renan.desafioandroid.model.data

import com.squareup.moshi.Json

data class RepositoryItemsList (
    @field:Json(name = "items") val repositoryItemsList: List<Repository>
)

data class Repository (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "owner") val owner: Owner,
    @field:Json(name = "stargazers_count") val starts: Int,
    @field:Json(name = "forks_count") val forks: Int,
    @field:Json(name = "full_name") val fullName: String
)

data class Owner (
    @field:Json(name = "login") val login: String,
    @field:Json(name = "avatar_url") val avatarUrl: String
)