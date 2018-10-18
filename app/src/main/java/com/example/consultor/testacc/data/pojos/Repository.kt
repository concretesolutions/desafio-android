package com.example.consultor.testacc.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Repository (
    @SerializedName("id")
    @Expose
    val id:String="",
    @SerializedName("name")
    @Expose
    val repoName:String="",
    @SerializedName("description")
    @Expose
    val repoDesc:String="",
    @SerializedName("issues_url")
    @Expose
    val issues:String="",
    @SerializedName("open_issues")
    @Expose
    val issuesOpen:Int=0,

    @SerializedName("stargazers_count")
    @Expose
    val reposStars:Int=0,
    @SerializedName("forks_count")
    @Expose
    val reposForks:Int=0,

    @SerializedName("owner")
    @Expose
    val owner:Owner=Owner()

):Serializable