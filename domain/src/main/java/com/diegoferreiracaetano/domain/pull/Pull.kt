package com.diegoferreiracaetano.domain.pull

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.domain.owner.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "pull")
data class Pull(@PrimaryKey
                val id:Long,
                val title:String,
                @SerializedName("created_at")
                val date:Date,
                val body:String?,
                @SerializedName("html_url")
                val url:String,
                @SerializedName("user")
                @Embedded(prefix = "user_")
                var owner: Owner,
                @ColumnInfo(name = "owner_name")
                var ownerName: String,
                @ColumnInfo(name = "repo_name")
                var repoName: String): Serializable {
    constructor():this(0,"",Date(),"","", Owner(), "","")
}