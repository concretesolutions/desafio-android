package com.diegoferreiracaetano.domain.repo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.domain.owner.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "repo",
        indices = arrayOf(Index(value = ["starts"])))
data class Repo (
        @PrimaryKey
        var id:Long,
        var name:String,
        var description:String?,
        @SerializedName("stargazers_count")
        var starts:Int,
        @SerializedName("forks_count")
        var forks:Int,
        @Embedded(prefix = "owner_")
        var owner: Owner) :Serializable{
        constructor():this(0,"","",0,0,Owner())

}
