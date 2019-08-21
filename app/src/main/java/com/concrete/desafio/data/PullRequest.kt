package com.concrete.desafio.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PullRequest(

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("title")
    val titulo: String,

    @Expose
    @SerializedName("head")
    val head: Head,

    @Expose
    @SerializedName("user")
    val autor: Autor,

    @Expose
    @SerializedName("body")
    val corpo: String,

    @Expose
    @SerializedName("created_at")
    val data: String

)

data class Head(

    @Expose
    @SerializedName("repo")
    val repositorio: Repositorio

)