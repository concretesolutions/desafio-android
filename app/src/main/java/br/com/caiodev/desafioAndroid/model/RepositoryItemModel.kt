package br.com.caiodev.desafioAndroid.model

import com.google.gson.annotations.SerializedName

class RepositoryItemModel {

    @SerializedName("id")
    val repoId: Long? = null

    @SerializedName("name")
    val repoName = ""

    @SerializedName("description")
    val description = ""

    @SerializedName("stargazers_count")
    val numberOfStars: Long? = null

    @SerializedName("forks_count")
    val numberOfForks: Long? = null

    @SerializedName("owner")
    val repoOwner: RepositoryOwnerModel? = null
}