package com.bassul.mel.app.repositoriesList.repository.model

import com.bassul.mel.app.domain.Owner
import com.google.gson.annotations.SerializedName

//Cada item da lista deve exibir Nome
// / Foto do autor do PR,
// Título do PR,
// Data do PR e Body do PR

data class PullRequestListResponse(
    @SerializedName("html_url")
    val html_url : String
  // val list : List<PR>
   // val url: String,
  //
    )


