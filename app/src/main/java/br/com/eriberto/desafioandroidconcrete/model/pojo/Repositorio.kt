package br.com.eriberto.desafioandroidconcrete.model.pojo

import com.google.gson.annotations.SerializedName

class Repositorio(
    @SerializedName("name")
    var nomeRepositorio: String,
    @SerializedName("description")
    var descricaoRepositorio: String,
    @SerializedName("owner")
    var proprietario: Proprietario,
    @SerializedName("score")
    var quantidadeDeEstrelas: Long,
    @SerializedName("forks")
    var quantidadeDeForks: Long
)