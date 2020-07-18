package com.jsouza.repodetail.data.remote.response

import com.squareup.moshi.Json

data class PullsResponse(
    var title: String? = "",
    var body: String? = "",
    @Json(name = "user")
    var owner: Owner,
    @Json(name = "html_url")
    var url: String? = "",
    var milestone: PullProperty?
)
