package com.jsouza.repodetail.data.remote.response

import com.squareup.moshi.Json

data class Owner(
    var avatar_url: String?,
    @Json(name = "login")
    var username: String?
)
