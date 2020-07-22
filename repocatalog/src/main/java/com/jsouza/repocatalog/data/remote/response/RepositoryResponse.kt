package com.jsouza.repocatalog.data.remote.response

import com.squareup.moshi.Json

data class RepositoryResponse(
    @Json(name = "total_count") val totalCount: Int = 0,
    val items: List<Repository> = emptyList(),
    var nextPage: Int? = null
)
