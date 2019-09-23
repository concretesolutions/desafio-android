package br.com.cavreti.desafio_android.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("login") var name: String,
    @SerializedName("avatar_url") var imageUrl: String
                 ) : Serializable {
}