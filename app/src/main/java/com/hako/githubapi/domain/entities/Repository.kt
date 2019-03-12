package com.hako.githubapi.domain.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories", indices = [Index(value = ["id"], unique = true)])
data class Repository(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    @Embedded
    val owner: User,
    @SerializedName("stargazers_count")
    val stars: String,
    @SerializedName("forks_count")
    val forks: String,
    var page: Int
)

data class Repositories(
    val items: List<Repository>
)
