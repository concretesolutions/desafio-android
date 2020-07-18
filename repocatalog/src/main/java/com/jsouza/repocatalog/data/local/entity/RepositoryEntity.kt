package com.jsouza.repocatalog.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "REPOSITORIES")
data class RepositoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val owner: String?,
    val fullName: String?,
    var description: String?,
    val stargazersCount: Int?,
    val forksCount: Int?,
    val pageNumber: Int?
)
