package com.jsouza.repocatalog.data.repocatalog.remote.response

import com.squareup.moshi.Json

data class RepositoryResponse(
    @Json(name = "total_count") val totalCount: Int = 0,
    val items: MutableList<Repository> = mutableListOf(),
    var nextPage: Int? = null
)
