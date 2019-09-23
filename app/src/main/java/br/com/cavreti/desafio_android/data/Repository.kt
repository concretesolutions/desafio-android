package br.com.cavreti.desafio_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository (

    var id: Int,
    var name: String,
    var description: String,
    var owner: User,
    var forks: Int,
    @SerializedName("stargazers_count") var stars: Int

    ) : Serializable