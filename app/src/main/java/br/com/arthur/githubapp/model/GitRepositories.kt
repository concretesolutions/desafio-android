package br.com.arthur.githubapp.model

import com.google.gson.annotations.SerializedName

class GitRepositories(

    @SerializedName("total_count")
    val totalCount: Long,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<GitRepository>

)