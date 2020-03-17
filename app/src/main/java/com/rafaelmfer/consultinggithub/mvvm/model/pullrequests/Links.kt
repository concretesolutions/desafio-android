package com.rafaelmfer.consultinggithub.mvvm.model.pullrequests

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self") val self: Self,
    @SerializedName("html") val html: Html,
    @SerializedName("issue") val issue: Issue,
    @SerializedName("comments") val comments: Comments,
    @SerializedName("review_comments") val reviewComments: ReviewComments,
    @SerializedName("review_comment") val reviewComment: ReviewComment,
    @SerializedName("commits") val commits: Commits,
    @SerializedName("statuses") val statuses: Statuses
)