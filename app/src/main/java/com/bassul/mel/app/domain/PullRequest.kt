package com.bassul.mel.app.domain

import java.io.Serializable

data class PullRequest(
    val html_url: String, //browser
    val updated_at: String, //Data do PR
    val body: String, //body do PR
    val userName: String, //Nome e Foto do autor do PR
    val userAvatar: String
) : Serializable //Precisa do Serializable para passar o objeto como parametro usando bundle