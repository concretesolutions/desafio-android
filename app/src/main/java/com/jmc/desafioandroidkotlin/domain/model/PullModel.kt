package com.jmc.desafioandroidkotlin.domain.model

import java.util.*

data class PullModel(
    val id: String,
    val title: String,
    val body: String,
    val number: Int,
    val url: String,
    val userModel: UserModel,
    val createdAt: Date,
    val updatedAt: Date,
    val closedAt: Date = Date(-1),
    val mergedAt: Date = Date(-1)
)