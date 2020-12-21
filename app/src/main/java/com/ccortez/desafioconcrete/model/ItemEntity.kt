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
    var forks_count: String?,
    var stargazers_count: String?,
    var full_name: String?
) {

    data class Owner(
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("url")
        val url: String?,
        @field:SerializedName("avatar_url")
        val avatar_url: String?
    )

    companion object {
        const val UNKNOWN_ID = -1
    }

}