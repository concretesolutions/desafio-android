package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class License {

    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("spdx_id")
    @Expose
    var spdxId: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null

}
