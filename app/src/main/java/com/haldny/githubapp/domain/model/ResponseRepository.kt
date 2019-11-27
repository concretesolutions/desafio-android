package com.haldny.githubapp.domain.model

import com.google.gson.annotations.SerializedName

data class ResponseRepository(@SerializedName("items") val items: List<Repository>,
                              @SerializedName("total_count") val totalCount: Long)