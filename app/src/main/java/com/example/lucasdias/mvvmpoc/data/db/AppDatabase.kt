package com.example.lucasdias.mvvmpoc.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import com.example.lucasdias.mvvmpoc.domain.entity.Repository

@Database(version = 1, entities = [Repository::class, PullRequest::class])

    abstract class AppDatabase : RoomDatabase() {
        abstract fun repositoryDao(): RepositoryDao
        abstract fun pullRequestDao(): PullRequestDao
}