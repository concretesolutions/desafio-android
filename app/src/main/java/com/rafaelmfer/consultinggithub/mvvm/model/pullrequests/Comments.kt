package com.rafaelmfer.consultinggithub.mvvm.model.pullrequests

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("href") val href: String
)