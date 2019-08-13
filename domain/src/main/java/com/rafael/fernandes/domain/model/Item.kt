package com.rafael.fernandes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Item(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("full_name")
    var fullName: String? = null,
    @SerializedName("owner")
    var owner: Owner? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("stargazers_count")
    var stargazersCount: Int? = null,
    @SerializedName("forks_count")
    var forksCount: Int? = null,
    @SerializedName("favorite")
    var favorite: Boolean
) : Serializable
