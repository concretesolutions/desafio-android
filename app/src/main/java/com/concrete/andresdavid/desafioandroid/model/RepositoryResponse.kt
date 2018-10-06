package com.concrete.andresdavid.desafioandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RepositoryResponse() {
    @SerializedName("total_count")
    @Expose
    private val totalCount: Int = 0

    @SerializedName("incomplete_results")
    @Expose
    private var incompleteResults: Boolean = false

    @SerializedName("items")
    @Expose
    var items: List<Repository> = emptyList()
}