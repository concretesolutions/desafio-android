package com.accenture.desafioandroid.mvvm.model

import com.accenture.desafioandroid.mvvm.model.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepositoryResponse {

    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    @Expose
    var items: MutableList<Item?>? = null

}