package com.example.desafioandroid.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchRepository {

        @SerializedName("total_count")
        @Expose
        var totalCount : Int? = 0

        @SerializedName("incomplete_results")
        @Expose
        var incompleteResults : Boolean? = false

        @SerializedName("items")
        @Expose
        var items : List<RepositoryItem>? = null
}
