package com.ccortez.desafioconcrete.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ItemEntity(
    @PrimaryKey
    var id: Int,
    var name: String?,
    var description: String?,
    @field:SerializedName("owner")
    @field:Embedded(prefix = "owner_")
    val owner: Owner,
    @field:SerializedName("forks_count")
    var forksCount: String?,
    @field:SerializedName("stargazers_count")
    var stargazersCount: String?,
    @field:SerializedName("full_name")
    var fullName: String?
) {

    data class Owner(
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("url")
        val url: String?,
        @field:SerializedName("avatar_url")
        val avatarUrl: String?
    )

    companion object {
        const val UNKNOWN_ID = -1
    }

}
