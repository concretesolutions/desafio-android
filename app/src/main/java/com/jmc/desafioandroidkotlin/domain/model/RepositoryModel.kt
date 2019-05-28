package com.jmc.desafioandroidkotlin.domain.model

import java.util.*

data class RepositoryModel(
    val id: String,
    val name: String,
    val fullName: String,
    val url: String,
    val description: String,
    val owner: UserModel,
    val stargazersCount: Int,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val forksCount: Int,
    val createdAt: Date,
    val updatedAt: Date,
    val page: Int
)