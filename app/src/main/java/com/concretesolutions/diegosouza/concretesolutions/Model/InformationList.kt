package com.concretesolutions.diegosouza.concretesolutions.Model

data class InformationList(
    val title: String,
    val html_url: String,
    val body: String,
    val repo: RepoInformation,
    val user: OwnerInformation
)

data class OwnerInformation(
    val login: String,
    val avatar_url: String
)

data class RepoInformation(val full_name: String)