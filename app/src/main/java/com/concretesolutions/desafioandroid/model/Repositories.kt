package com.concretesolutions.desafioandroid.model

import com.google.gson.annotations.SerializedName

data class Repositories(
    @SerializedName("total_count")
    var totalRepositories: Number,
    @SerializedName("items")
    var repositories: List<Repository>
)