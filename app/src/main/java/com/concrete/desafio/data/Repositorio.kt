package com.concrete.desafio.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repositorio(

    @Expose
    @SerializedName("name")
    val nome: String
)

data class Repositorios(

    @Expose
    @SerializedName("items")
    val repositorios: List<Repositorio>
)