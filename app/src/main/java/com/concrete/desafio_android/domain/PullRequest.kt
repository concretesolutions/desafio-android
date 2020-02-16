package com.concrete.desafio_android.domain

import java.util.Date

class PullRequest(
    val user: User,
    val title: String,
    val body: String,
    val html_url: String,
    val updated_at: Date
)