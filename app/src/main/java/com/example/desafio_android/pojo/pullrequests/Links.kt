package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links {

    @SerializedName("self")
    @Expose
    var self: Self? = null
    @SerializedName("html")
    @Expose
    var html: Html? = null
    @SerializedName("issue")
    @Expose
    var issue: Issue? = null
    @SerializedName("comments")
    @Expose
    var comments: Comments? = null
    @SerializedName("review_comments")
    @Expose
    var reviewComments: ReviewComments? = null
    @SerializedName("review_comment")
    @Expose
    var reviewComment: ReviewComment? = null
    @SerializedName("commits")
    @Expose
    var commits: Commits? = null
    @SerializedName("statuses")
    @Expose
    var statuses: Statuses? = null

}
