package com.jsouza.repocatalog.data.repocatalog.remote.response

import com.squareup.moshi.Json

data class Owner(
    val login: String?,
    @Json(name = "avatar_url") val avatarUrl: String?
)
