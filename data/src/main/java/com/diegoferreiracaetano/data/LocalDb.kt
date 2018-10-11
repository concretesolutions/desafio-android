package com.diegoferreiracaetano.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diegoferreiracaetano.data.converters.DateConverter
import com.diegoferreiracaetano.data.pull.PullDao
import com.diegoferreiracaetano.data.repo.RepoDao
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.repo.Repo

@Database(entities = [Repo::class,
                      Pull::class],
        version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LocalDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun pullDao(): PullDao
}