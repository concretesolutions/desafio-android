package com.gdavidpb.github.data.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.gdavidpb.github.utils.TABLE_PULLS

@Entity(
    tableName = TABLE_PULLS,
    foreignKeys = [ForeignKey(
        entity = RepositoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("rid"),
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class PullEntity(
    @PrimaryKey val id: Long,
    val rid: Long,
    val title: String,
    val body: String,
    val repository: String,
    val number: Int,
    val url: String,
    @Embedded val user: EmbeddedUser,
    val createdAt: Long,
    val updatedAt: Long,
    val closedAt: Long,
    val mergedAt: Long
)