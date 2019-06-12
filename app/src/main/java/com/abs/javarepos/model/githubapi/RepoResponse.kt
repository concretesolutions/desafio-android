package com.abs.javarepos.model.githubapi

import com.abs.javarepos.model.Repo

data class RepoResponse(
    val items: List<Repo>
)