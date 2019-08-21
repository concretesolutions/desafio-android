package com.concrete.desafio.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repositorio(

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("name")
    val nome: String,

    @Expose
    @SerializedName("description")
    val descricao: String,

    @Expose
    @SerializedName("forks_count")
    val forks: Long,

    @Expose
    @SerializedName("stargazers_count")
    val estrelas: Long,

    @Expose
    @SerializedName("owner")
    val autor: Autor,

    @Expose
    @SerializedName("full_name")
    val nomeCompleto: String

)

data class Repositorios(

    @Expose
    @SerializedName("items")
    val repositorios: List<Repositorio>
)