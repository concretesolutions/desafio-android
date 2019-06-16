package com.concretesolutions.diegosouza.concretesolutions.Model

import java.util.*

data class InformationList(
    val title: String,
    val html_url: String,
    val body: String,
    val base: BaseInformation,
    val user: OwnerInformation,
    val created_at: Date
)

data class OwnerInformation(
    val login: String,
    val avatar_url: String
)

data class BaseInformation(val repo: RepoInformation)
data class RepoInformation(val full_name: String)