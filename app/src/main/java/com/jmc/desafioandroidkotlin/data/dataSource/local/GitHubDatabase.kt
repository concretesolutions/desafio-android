package com.jmc.desafioandroidkotlin.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmc.desafioandroidkotlin.data.model.database.PullEntity
import com.jmc.desafioandroidkotlin.data.model.database.RepositoryEntity
import com.jmc.desafioandroidkotlin.data.dataSource.local.dao.PullDao
import com.jmc.desafioandroidkotlin.data.dataSource.local.dao.RepositoryDao
import com.jmc.desafioandroidkotlin.utils.DATABASE_VERSION


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