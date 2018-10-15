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

    @SerializedName("html_url")
    var htmlUrl: String? = null

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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as User

            if (avatarUrl != other.avatarUrl) return false
            if (login != other.login) return false

            return true
        }

        override fun hashCode(): Int {
            var result = avatarUrl?.hashCode() ?: 0
            result = 31 * result + (login?.hashCode() ?: 0)
            return result
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PullTO

        if (body != other.body) return false
        if (id != other.id) return false
        if (repoName != other.repoName) return false
        if (repoOwnerLogin != other.repoOwnerLogin) return false
        if (title != other.title) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = body?.hashCode() ?: 0
        result = 31 * result + id.hashCode()
        result = 31 * result + (repoName?.hashCode() ?: 0)
        result = 31 * result + (repoOwnerLogin?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }
}