package com.example.desafioandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.desafioandroid.data.model.OwnerModel
import com.example.desafioandroid.domain.model.Repo

@Entity(tableName = "repo_table")
data class RepoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idRepo") val idRepo: Int,
    @ColumnInfo(name = "nameRepo") val nameRepo: String,
    @ColumnInfo(name = "descriptionRepo") val descriptionRepo: String,
    @ColumnInfo(name = "number") val fullNameRepo: String,
    @ColumnInfo(name = "stars") val stars: Int = 0,
    @ColumnInfo(name = "forks") val forks: Int = 0,
    @Embedded val owner_repos: OwnerModel
)

fun Repo.toDatabase() =
    RepoEntity(idRepo, nameRepo, descriptionRepo, fullNameRepo, stars, forks, owner_repos)