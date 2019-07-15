package com.pedrenrique.githubapp.core.net

import com.pedrenrique.githubapp.core.data.Repository

data class RepositoryResponse(
    val items: List<Repository>
)