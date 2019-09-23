package br.com.cavreti.desafio_android.network.response

import br.com.cavreti.desafio_android.data.Repository
import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("total_count") var totalCount: Int,
    var items: List<Repository>)