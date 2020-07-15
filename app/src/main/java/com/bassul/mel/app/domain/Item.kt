package com.bassul.mel.app.domain

import java.io.Serializable

data class Item(
    val id: Int,
    val name: String,
    val owner: Owner,
    val stargazers_count: String,
    val forks_count: String,
    val description: String?,
    val pulls_url: String
) : Serializable