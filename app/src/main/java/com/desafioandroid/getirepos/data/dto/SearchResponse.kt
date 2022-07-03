package com.desafioandroid.getirepos.data.dto

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_response")
    val totalResponse: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Repo>
)
