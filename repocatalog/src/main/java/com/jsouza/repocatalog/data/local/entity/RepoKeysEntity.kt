package com.jsouza.repocatalog.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "REPO_KEYS")
data class RepoKeysEntity(
    @PrimaryKey
    val repositoryId: Long,
    val previousKey: Int?,
    val nextKey: Int?
)
