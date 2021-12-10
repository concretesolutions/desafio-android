package com.example.desafio_android.data.pullmodel

data class Head(
    val label: String,
    val ref: String,
    val repo: RepoX,
    val sha: String,
    val user: UserX
)