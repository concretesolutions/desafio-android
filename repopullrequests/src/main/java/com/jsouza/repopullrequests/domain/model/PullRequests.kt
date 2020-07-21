package com.jsouza.repopullrequests.domain.model

import com.jsouza.repopullrequests.data.remote.response.Owner

data class PullRequests(
    var id: Int,
    var url: String?,
    var title: String?,
    var body: String?,
    var owner: Owner?,
    var createdAt: String?,
    var state: String?,
    var repositoryId: Long?
)
