package com.example.desafioconcentresolutions.models

import java.net.URI

data class GitHubRepository(
    val imageURI: String,
    val name:String,
    val description:String,
    val forks:Int,
    val stars:Int,
    val username:String,
    val fullName:String
) {
}