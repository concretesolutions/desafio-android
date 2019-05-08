package com.gdavidpb.github.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdavidpb.github.data.model.database.PullEntity
import com.gdavidpb.github.data.model.database.RepositoryEntity
import com.gdavidpb.github.data.source.local.dao.PullDao
import com.gdavidpb.github.data.source.local.dao.RepositoryDao
import com.gdavidpb.github.utils.DATABASE_VERSION

@Database(
    entities = [
        RepositoryEntity::class,
        PullEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract val repositories: RepositoryDao
    abstract val pulls: PullDao
}