package com.rafaelmfer.consultinggithub.mvvm.model.pullrequests

import com.google.gson.annotations.SerializedName

data class ReviewComments(
    @SerializedName("href") val href: String
)