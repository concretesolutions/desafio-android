package com.jmc.desafioandroidkotlin.domain.model

data class UserModel(
    val id: Long,
    val login: String,
    val url: String,
    val avatarUrl: String
)