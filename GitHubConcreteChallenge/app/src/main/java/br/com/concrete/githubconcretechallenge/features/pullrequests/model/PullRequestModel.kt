package br.com.concrete.githubconcretechallenge.features.pullrequests.model

import br.com.concrete.githubconcretechallenge.features.repositories.model.UserModel
import com.google.gson.annotations.SerializedName

data class PullRequestModel(val id: Long,
                            val title: String,
                            val body: String,
                            @SerializedName("html_url") val htmlUrl: String,
                            val user: UserModel,
                            val state: String)
