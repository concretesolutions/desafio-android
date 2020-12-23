package com.ccortez.desafioconcreteapplication.service.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    var items: List<Items>? = null,
    @field:SerializedName("total_count")
    var totalCount: Int
)