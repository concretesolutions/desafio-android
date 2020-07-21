package com.jsouza.repodetail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsouza.repodetail.data.local.dao.PullsDao
import com.jsouza.repodetail.data.local.entity.PullsEntity

@Database(
    entities = [PullsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PullsDatabase : RoomDatabase() {

    abstract fun pullsDao(): PullsDao
}
