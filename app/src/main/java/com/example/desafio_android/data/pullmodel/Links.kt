package com.example.desafio_android.data.pullmodel

data class Links(
    val comments: Comments,
    val commits: Commits,
    val html: Html,
    val issue: Issue,
    val review_comment: ReviewComment,
    val review_comments: ReviewComments,
    val self: Self,
    val statuses: Statuses
)