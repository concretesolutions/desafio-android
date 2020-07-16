package com.bassul.mel.app.domain

import java.io.Serializable

data class PullRequest(
    val html_url: String,
    val updated_at: String,
    val body: String,
    val userName: String,
    val userAvatar: String
) : Serializable