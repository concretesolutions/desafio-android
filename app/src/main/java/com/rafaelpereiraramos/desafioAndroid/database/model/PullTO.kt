package com.rafaelpereiraramos.desafioAndroid.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
@Entity(tableName = "pull")
class PullTO {
    @SerializedName("body")
    var body: String? = null

    @PrimaryKey
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("title")
    var title: String? = null

    @Embedded
    @SerializedName("user")
    var user: User? = null

    class User {
        @SerializedName("avatar_url")
        var avatarUrl: String? = null

        @SerializedName("login")
        var login: String? = null
    }
}