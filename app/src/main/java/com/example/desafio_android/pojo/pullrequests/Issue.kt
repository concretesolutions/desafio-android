package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Issue {

    @SerializedName("href")
    @Expose
    var href: String? = null

}
