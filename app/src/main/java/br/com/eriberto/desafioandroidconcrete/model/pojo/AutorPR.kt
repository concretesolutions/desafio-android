package br.com.eriberto.desafioandroidconcrete.model.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AutorPR(
    @SerializedName("login")
    var nome: String,
    @SerializedName("avatar_url")
    var urlFoto: String,
    @SerializedName("html_url")
    var urlSite: String
) : Serializable