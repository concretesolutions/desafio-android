package com.jsouza.repopullrequests.data.remote.response

import com.squareup.moshi.Json

data class OwnerResponse(
    var avatar_url: String?,
    @Json(name = "login") var username: String?
)
