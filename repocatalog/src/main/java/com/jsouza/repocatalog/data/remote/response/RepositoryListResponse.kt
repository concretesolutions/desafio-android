package com.jsouza.repocatalog.data.remote.response

import com.squareup.moshi.Json

data class RepositoryListResponse(
    @Json(name = "total_count") val totalCount: Int = 0,
    val items: List<RepositoryResponse> = emptyList(),
    var nextPage: Int? = null
)
