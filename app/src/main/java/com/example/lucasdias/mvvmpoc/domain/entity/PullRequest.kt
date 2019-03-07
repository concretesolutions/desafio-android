package com.example.lucasdias.mvvmpoc.domain.entity

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

@Entity(tableName = "pullRequest")

data class PullRequest(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long,
                       @ColumnInfo(name = "title") var title: String?,
                       @ColumnInfo(name = "body") @SerializedName("body") var description: String?,
                       @ColumnInfo(name = "created_at") @SerializedName("created_at") var createdAt: String?,
                       @ColumnInfo(name = "html_url") @SerializedName("html_url") var htmlUrl: String?,
                       @ColumnInfo(name = "repository_id") var repositoryId: String?,
                       @Embedded var user: User?){

    fun getCreatedAtDateString(): String{

        val isEmpty = createdAt?.isEmpty() ?: true
        return if(!isEmpty) {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = format.parse(createdAt)
            val brazilFormat = SimpleDateFormat("dd/MM/yyyy")
            brazilFormat.format(date.time).toString()
        }
        else{
            ""
        }
    }
}

