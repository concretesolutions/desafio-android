package com.hako.githubapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.githubapi.data.database.dao.RepositoryDao
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.entities.User

@Database(entities = [User::class, Repository::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
