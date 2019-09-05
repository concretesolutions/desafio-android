package com.example.desafio_android.model

import java.io.Serializable

class PullRequestModel (
    var name: String? = "",
    var description: String? = "",
    var createdAt: String? = "",
    var login: String? = "",
    var avatarUrl: String? = "") : Serializable
