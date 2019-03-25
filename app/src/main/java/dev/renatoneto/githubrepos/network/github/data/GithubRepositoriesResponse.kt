package dev.renatoneto.githubrepos.network.github.data

import com.google.gson.annotations.SerializedName
import dev.renatoneto.githubrepos.model.github.GithubRepository

data class GithubRepositoriesResponse(@SerializedName("items") val items: ArrayList<GithubRepository>)