package com.example.desafioandroid.schemas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.text.Html


class Links {

    @SerializedName("self")
    @Expose
    var self: Href? = null
    @SerializedName("html")
    @Expose
    var html: Href? = null
    @SerializedName("issue")
    @Expose
    var issue: Href? = null
    @SerializedName("comments")
    @Expose
    var comments: Href? = null
    @SerializedName("review_comments")
    @Expose
    var reviewComments: Href? = null
    @SerializedName("review_comment")
    @Expose
    var reviewComment: Href? = null
    @SerializedName("commits")
    @Expose
    var commits: Href? = null
    @SerializedName("statuses")
    @Expose
    var statuses: Href? = null

}