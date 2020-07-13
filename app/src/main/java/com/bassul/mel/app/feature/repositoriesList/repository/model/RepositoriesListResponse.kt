package com.bassul.mel.app.feature.repositoriesList.repository.model

import com.bassul.mel.app.domain.Owner

data class RepositoriesListResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<ItemResponse>,
        val message: String
    )

    data class ItemResponse(
        val id: Int,
        val name: String,
        val owner: Owner,
        val stargazers_count: String,
        val forks_count: String,
        val description : String?,
        val pulls_url : String
    )
