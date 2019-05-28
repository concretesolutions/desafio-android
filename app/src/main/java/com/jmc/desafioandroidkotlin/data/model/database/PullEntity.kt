package com.jmc.desafioandroidkotlin.data.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.jmc.desafioandroidkotlin.utils.TABLE_PULLS

@Entity(
    tableName = TABLE_PULLS
)
data class PullEntity(
    @PrimaryKey val id: String,
    val repository: String,
    val title: String,
    val body: String,
    val number: Int,
    val url: String,
    @Embedded val user: EmbeddedUser,
    val createdAt: Long,
    val updatedAt: Long,
    val closedAt: Long,
    val mergedAt: Long
)