package br.edu.ifsp.scl.desafio_android.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("total_count") var total_count: Long,
    @SerializedName("incomplete_results") var incomplete_results: Boolean,
    @SerializedName("items") var items: LinkedHashSet<Repository?>? = linkedSetOf()
)