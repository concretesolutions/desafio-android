package com.jsouza.repopullrequests.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PULL_REQUESTS_TABLE")
data class PullsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var repositoryId: Long?,
    var url: String?,
    var title: String?,
    var body: String?,
    var owner: String?,
    var createdAt: String?,
    var state: String?
)
