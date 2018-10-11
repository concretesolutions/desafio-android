package com.diegoferreiracaetano.domain.owner

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "owner")
data class Owner(var id:Long,
                 @SerializedName("login")
                 var name:String,
                 @SerializedName("avatar_url")
                 var photo:String){
    constructor() : this(0,"","")
}