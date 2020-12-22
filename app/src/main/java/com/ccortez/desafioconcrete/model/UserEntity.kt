package com.ccortez.desafioconcrete.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,

    @field:SerializedName("first_name")
    var firstName: String,

    var email: String,

    @field:SerializedName("last_name")
    var lastName: String,

    var avatar: String
)
