package br.com.cavreti.desafio_android.data

import com.google.gson.annotations.SerializedName

class PullRequest(
    @SerializedName("html_url") val url: String?,
    val title: String?,
    @SerializedName("body") val description: String?,
    val user: User?)