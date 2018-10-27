package br.com.andreyneto.desafioandroid.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    var items: List<Repo>)

data class Repo(
    var id: Long,
    var name: String,
    var description: String,
    var forks: Int,
    var owner: User,
    @SerializedName("stargazers_count") var stars: Int)

data class User(
        @SerializedName("login") var name: String,
        @SerializedName("avatar_url") var profilePic: String,
        @SerializedName("name") var fullName: String)

data class Pull(
        var title: String,
        var body: String,
        @SerializedName("html_url") var url: String)