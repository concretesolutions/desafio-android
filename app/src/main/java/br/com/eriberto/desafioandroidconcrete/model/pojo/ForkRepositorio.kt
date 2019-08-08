package br.com.eriberto.desafioandroidconcrete.model.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ForkRepositorio(
    @SerializedName("user")
    var autorPR: AutorPR,
    @SerializedName("title")
    var titulo:String,
    @SerializedName("body")
    var descricao:String,
    @SerializedName("head")
    var cabecalho: Cabecalho
) : Serializable