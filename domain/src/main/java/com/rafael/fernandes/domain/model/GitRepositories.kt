package com.rafael.fernandes.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GitRepositories(
    @SerializedName("total_count")
    var totalCount: Int? = null,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null,
    @SerializedName("items")
    var items: List<Item>? = null
) : Serializable {
    companion object {
        const val UNKNOWN_ID = -1
    }
}