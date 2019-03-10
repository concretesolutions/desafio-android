package com.hako.githubapi.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.githubapi.data.repository.database.dao.PullRequestDao
import com.hako.githubapi.data.repository.database.dao.RepositoryDao
import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.entities.User

@Database(entities = [User::class, PullRequest::class, Repository::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
    abstract fun pullrequestDao(): PullRequestDao
}
