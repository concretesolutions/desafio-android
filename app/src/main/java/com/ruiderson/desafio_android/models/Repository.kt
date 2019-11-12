package com.ruiderson.desafio_android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class RepositoryBody(
    val items: ArrayList<Repository>
)


@Parcelize
data class Repository (
    val id: Long,
    val name: String,
    val description: String,
    val forks: Int,
    val stargazers_count: Int,
    val owner: RepositoryOwner
) : Parcelable
{
    constructor(cache: RepositoryCache) : this(
        cache.id,
        cache.name,
        cache.description,
        cache.forks,
        cache.stargazers_count,
        RepositoryOwner(cache.owner_avatar_url, cache.owner_login))
}


@Parcelize
data class RepositoryOwner (
    val avatar_url: String,
    val login: String
) : Parcelable
