package com.hako.githubapi.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @SerializedName("id")
    val userId: String,
    @SerializedName("login")
    val author: String,
    @SerializedName("avatar_url")
    val thumbnail: String
)
