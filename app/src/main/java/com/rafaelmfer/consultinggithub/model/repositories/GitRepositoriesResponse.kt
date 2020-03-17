package com.rafaelmfer.consultinggithub.model.repositories

import com.google.gson.annotations.SerializedName

data class GitRepositoriesResponse(
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("total_count") val totalCount: Int
)