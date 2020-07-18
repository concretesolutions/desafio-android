package com.jsouza.repodetail.data.remote.response

import com.squareup.moshi.Json

data class PullProperty(
    @Json(name = "open_issues")
    var open: Int?,
    @Json(name = "closed_issues")
    var closed: Int?
)
