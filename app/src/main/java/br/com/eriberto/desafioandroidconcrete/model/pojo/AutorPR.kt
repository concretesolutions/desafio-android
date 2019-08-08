package br.com.eriberto.desafioandroidconcrete.model.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AutorPR(
    @SerializedName("BrkingYan")
    var nome: String,
    @SerializedName("avatar_url")
    var urlFoto: String,
    @SerializedName("")
    var urlSite: String
) : Serializable