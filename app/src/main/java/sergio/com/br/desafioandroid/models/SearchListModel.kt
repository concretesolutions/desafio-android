package sergio.com.br.desafioandroid.models

import com.google.gson.annotations.SerializedName

data class SearchListModel(
    @SerializedName("items") var itemsList: ArrayList<GitItemsModel>
)