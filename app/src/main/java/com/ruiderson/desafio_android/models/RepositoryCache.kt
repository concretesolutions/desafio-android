package com.ruiderson.desafio_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RepositoryCache(
    @PrimaryKey
    val id: Long,
    val ordem: Long,
    val name: String,
    val description: String,
    val forks: Int,
    val stargazers_count: Int,
    val owner_avatar_url: String,
    val owner_login: String
)
{
    constructor(repository: Repository, order: Long) : this(
        repository.id,
        order,
        repository.name,
        repository.description,
        repository.forks,
        repository.stargazers_count,
        repository.owner.avatar_url,
        repository.owner.login)
}