package cl.mauledev.github.data.model

import com.google.gson.annotations.SerializedName

data class Response(var items: List<Repo>,
                    @SerializedName("total_count") var totalCount: Int,
                    @SerializedName("incomplete_results") var incompleteResults: Boolean)