package com.example.desafio_android.model

import java.io.Serializable

class RepositorieModel(
    var name: String? = "",
    var description: String? = "",
    var name_autor: String? = "",
    var login: String? = "",
    var forksCount: Int? = 0,
    var stargazersCount: Int? = 0,
    var avatarUrl: String? = "") : Serializable
