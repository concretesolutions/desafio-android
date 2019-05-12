package com.gdavidpb.github.data.model.database

import androidx.room.ColumnInfo

data class EmbeddedUser(
    @ColumnInfo(name = "user_id") val id: Long,
    @ColumnInfo(name = "user_login") val login: String,
    @ColumnInfo(name = "user_url") val url: String,
    @ColumnInfo(name = "user_avatar_url") val avatarUrl: String
)