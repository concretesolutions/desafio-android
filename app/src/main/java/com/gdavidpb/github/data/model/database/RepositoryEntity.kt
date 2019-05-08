package com.gdavidpb.github.data.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gdavidpb.github.utils.TABLE_REPOSITORIES

@Entity(
    tableName = TABLE_REPOSITORIES
)
data class RepositoryEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val description: String,
    @Embedded val owner: EmbeddedUser,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val forksCount: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val page: Int
)