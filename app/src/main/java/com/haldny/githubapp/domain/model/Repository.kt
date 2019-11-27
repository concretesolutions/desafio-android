package com.haldny.githubapp.domain.model

import com.google.gson.annotations.SerializedName

data class Repository(@SerializedName("id") val id: Int,
                      @SerializedName("name") val name: String,
                      @SerializedName("description") val description: String,
                      @SerializedName("forks_count") val forks: Int,
                      @SerializedName("stargazers_count") val stars: Int,
                      @SerializedName("created_at") val createdAt: String,
                      @SerializedName("owner") val owner: Owner)