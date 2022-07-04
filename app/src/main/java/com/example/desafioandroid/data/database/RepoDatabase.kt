package com.example.desafioandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.desafioandroid.data.database.dao.RepoDao
import com.example.desafioandroid.data.database.entities.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RepoDao
}