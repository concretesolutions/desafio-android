package com.hotmail.fignunes.desafioconcretesolution.repository.remote.pullresquest.responses

data class PullRequestResponses(
    val id: Long,
    val title: String,
    val user: PullRequestUserResponses,
    val body: String,
    val created_at: String,
    val state: String,
    val html_url: String,
    val message: String
)