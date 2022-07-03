package com.example.desafioandroid.domain.model

import com.example.desafioandroid.data.database.entities.RepoEntity
import com.example.desafioandroid.data.model.OwnerModel
import com.example.desafioandroid.data.model.RepoModel

data class Repo(
    val idRepo: Int,
    val nameRepo: String,
    val descriptionRepo: String,
    val fullNameRepo: String,
    val owner_repos: OwnerModel,
    val stars: Int = 0,
    val forks: Int = 0,
)

fun RepoModel.toDomain() = Repo(
    idRepo, nameRepo, descriptionRepo, fullNameRepo, owner_repos,stars ,forks
)

fun RepoEntity.toDomain() = Repo(
    idRepo, nameRepo, descriptionRepo, fullNameRepo, owner_repos, stars ,forks
)
