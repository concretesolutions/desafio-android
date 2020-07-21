package com.jsouza.repocatalog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsouza.repocatalog.data.local.dao.KeysDao
import com.jsouza.repocatalog.data.local.dao.RepoDao
import com.jsouza.repocatalog.data.local.entity.RepoKeysEntity
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity

@Database(
    entities = [RepositoryEntity::class,
        RepoKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun reposDao(): RepoDao
    abstract fun keysDao(): KeysDao
}
