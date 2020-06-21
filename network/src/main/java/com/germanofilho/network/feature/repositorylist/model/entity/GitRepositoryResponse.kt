package com.germanofilho.network.feature.repositorylist.model.entity

import com.google.gson.annotations.SerializedName

data class GitRepositoryResponse(@SerializedName("items") var items: List<Item>)

data class Item(@SerializedName("name") var name: String?,
                 @SerializedName("full_name") var fullName: String?,
                 @SerializedName("description") var description: String?,
                 @SerializedName("stargazers_count") var stargazersCount: Int?,
                 @SerializedName("forks_count") var forksCount: Int?,
                 @SerializedName("owner") var owner: Owner?)

data class Owner(@SerializedName("login") var login: String?,
                 @SerializedName("avatar_url") var avatarUrl: String?)


