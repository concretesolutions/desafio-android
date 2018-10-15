package com.rafaelpereiraramos.desafioAndroid.database.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
@Entity(tableName = "pull",
        foreignKeys = [ForeignKey(entity = RepoTO::class,
                parentColumns = ["owner_login"],
                childColumns = ["owner_login"],
                onDelete = ForeignKey.CASCADE)],
        indices = [Index(value = ["owner_login"])]
)
class PullTO {
    @SerializedName("body")
    var body: String? = null

    @PrimaryKey
    @SerializedName("id")
    lateinit var id: String

    @ColumnInfo(name = "repo_name")
    var repoName: String? = null

    @ColumnInfo(name = "owner_login")
    var repoOwnerLogin: String? = null

    @SerializedName("title")
    var title: String? = null

    @Embedded(prefix = "user_")
    @SerializedName("user")
    var user: User? = null

    class User {
        @SerializedName("avatar_url")
        var avatarUrl: String? = null

        @SerializedName("login")
        var login: String? = null
    }
}