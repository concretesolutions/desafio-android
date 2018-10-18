package com.example.consultor.testacc.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepoPage (

    @SerializedName("total_count")
    @Expose
    val count:Int,

    @SerializedName("imcomplete_results")
    @Expose
    val incomplete:Boolean,

    @SerializedName("items")
    @Expose
    val items:MutableList<Repository>
)