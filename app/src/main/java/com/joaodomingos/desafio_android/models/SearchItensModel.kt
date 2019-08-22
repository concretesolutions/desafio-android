package com.joaodomingos.desafio_android.models

import com.google.gson.annotations.SerializedName

data class SearchItensModel (
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val starsCount: Long,
    @SerializedName("forks_count") val forksCount: Long,
    @SerializedName("owner") val owner: OwnerModel,
    @SerializedName("pulls_url") val pullUrl: String
)