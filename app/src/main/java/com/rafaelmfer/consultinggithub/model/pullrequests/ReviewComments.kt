package com.rafaelmfer.consultinggithub.model.pullrequests

import com.google.gson.annotations.SerializedName

data class ReviewComments(
    @SerializedName("href") val href: String
)