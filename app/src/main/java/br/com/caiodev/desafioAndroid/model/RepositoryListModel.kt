package br.com.caiodev.desafioAndroid.model

import com.google.gson.annotations.SerializedName

class RepositoryListModel {

    @SerializedName("items")
    val content: MutableList<RepositoryItemModel>? = null
}