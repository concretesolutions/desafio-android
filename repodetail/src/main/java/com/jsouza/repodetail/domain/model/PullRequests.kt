package com.jsouza.repodetail.domain.model

import com.jsouza.repodetail.data.remote.response.Owner

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
