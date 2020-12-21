package com.ccortez.desafioconcrete.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,

    var first_name: String,

    var email: String,

    var last_name: String,

    var avatar: String
) {

}