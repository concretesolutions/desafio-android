package com.rafaelmfer.consultinggithub.model.pullrequests

import com.google.gson.annotations.SerializedName

data class ReviewComment(
    @SerializedName("href") val href: String
)