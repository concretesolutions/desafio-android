package com.germanofilho.network.feature.pullrequestlist.model.entity

import com.google.gson.annotations.SerializedName

data class GitPullRequestResponse(@SerializedName("title") var title: String,
                                  @SerializedName("body") var body: String,
                                  @SerializedName("html_url") var htmlUrl: String,
                                  @SerializedName("user") var user: User)

data class User(@SerializedName("login") var login: String?,
                @SerializedName("avatar_url") var avatarUrl: String?)