package com.rafaelpereiraramos.desafioAndroid.api

import com.google.gson.annotations.SerializedName
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
data class RepoSearchResponse(
        @SerializedName("total_count") val total: Int = 0,
        @SerializedName("items") val items: List<RepoTO> = emptyList(),
        val nextPage: Int? = null
) {
}