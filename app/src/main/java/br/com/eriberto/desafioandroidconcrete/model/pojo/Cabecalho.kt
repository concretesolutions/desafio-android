package br.com.eriberto.desafioandroidconcrete.model.pojo

import com.google.gson.annotations.SerializedName

class Cabecalho(
    @SerializedName("repo")
    var repositorio: Repositorio
)