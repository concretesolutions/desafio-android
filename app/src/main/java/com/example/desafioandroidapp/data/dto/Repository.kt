package com.example.desafioandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @SerializedName("items")
    val repositoryItems : List<RepositoryItem>
)
