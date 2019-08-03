package br.edu.ifsp.scl.desafio_android.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("total_count") val total_count: Int,
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val items: LinkedHashSet<Repository?>? = linkedSetOf()
)