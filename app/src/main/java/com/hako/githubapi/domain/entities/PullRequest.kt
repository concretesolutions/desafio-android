package com.hako.githubapi.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pull_requests", indices = [Index(value = ["id"], unique = true)])
data class PullRequest(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("user")
    @Embedded
    val user: User,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val description: String,
    @SerializedName("url")
    val url: String
)
