package com.rafaelpereiraramos.desafioAndroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafaelpereiraramos.desafioAndroid.database.dao.PullDAO
import com.rafaelpereiraramos.desafioAndroid.database.dao.RepoDAO
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Database(entities = [RepoTO::class, PullTO::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = "desafio-android.db"
    }

    abstract fun reposDao(): RepoDAO

    abstract fun pullDao(): PullDAO
}