package com.joaoibarra.gitapp.api.model

import com.google.gson.annotations.SerializedName

class Links (
    @SerializedName("self") val self: Link,
    @SerializedName("html") val html: Link,
    @SerializedName("issue") val issue: Link,
    @SerializedName("comments") val comments: Link,
    @SerializedName("review_comments") val reviewComments: Link,
    @SerializedName("review_comment") val reviewComment: Link,
    @SerializedName("commits") val commits: Link,
    @SerializedName("statuses") val statuses: Link
)
