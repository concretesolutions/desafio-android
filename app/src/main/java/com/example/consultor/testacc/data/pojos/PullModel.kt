package com.example.consultor.testacc.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PullModel(
    @SerializedName("html_url")
    @Expose
    val url:String="",
    @SerializedName("title")
    @Expose
    val title:String="",
    @SerializedName("body")
    @Expose
    val body:String="",
    @SerializedName("number")
    @Expose
    val number:Int=0,
    @SerializedName("created_at")
    @Expose
    val creationDate: String ="",
    @SerializedName("user")
    @Expose
    val user:User=User()

)
