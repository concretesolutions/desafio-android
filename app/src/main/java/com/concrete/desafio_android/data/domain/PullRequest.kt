package com.concrete.desafio_android.data.domain

import java.util.Date

class PullRequest(
    val user: User,
    val title: String,
    val body: String,
    val html_url: String,
    val created_at: Date
)