package com.bassul.mel.app.feature.pullRequestList.repository.model

//Cada item da lista deve exibir Nome
// / Foto do autor do PR,
// Título do PR,
// Data do PR e Body do PR

data class PullRequestListResponse(
    val html_url : String, //browser
    val updated_at : String, //Data do PR
    val body : String, //body do PR
    val user : UserResponse //
    )

data class UserResponse(
    val login : String,
    val avatar_url : String
)

