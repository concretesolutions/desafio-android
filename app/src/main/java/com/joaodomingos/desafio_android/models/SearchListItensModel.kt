package com.joaodomingos.desafio_android.models

import com.google.gson.annotations.SerializedName

data class SearchListItensModel (
    @SerializedName("items") var itemsList: ArrayList<SearchItensModel>
)