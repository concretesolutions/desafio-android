package com.ccortez.desafioconcrete.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    var items: List<ItemEntity>? = null,
    @field:SerializedName("total_count")
    var totalCount: Int
)
