package com.example.desafioandroid.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepositoryItem {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int? = null
    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Int? = null
    @SerializedName("forks_count")
    @Expose
    var forksCount: Int? = null
    @SerializedName("score")
    @Expose
    var score: Double? = null

}
