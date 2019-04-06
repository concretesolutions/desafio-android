package com.example.desafioandroid.schemas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName( "message" )
    @Expose
    var message : String? = null

    @SerializedName( "error" )
    @Expose
    var error: String? = null

    @SerializedName( "status" )
    @Expose
    var status : Int = 0
}