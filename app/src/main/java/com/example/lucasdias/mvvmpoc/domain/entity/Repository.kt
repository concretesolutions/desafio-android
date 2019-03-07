package com.example.lucasdias.mvvmpoc.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repository")

data class Repository(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long,
                      @ColumnInfo(name = "full_name") @SerializedName("full_name") var fullName: String?,
                      @ColumnInfo(name = "name") var name: String?,
                      @ColumnInfo(name = "description") var description: String?,
                      @ColumnInfo(name = "stargazers_count") @SerializedName("stargazers_count") var stargazersCount: Int?,
                      @ColumnInfo(name = "forks_count") @SerializedName("forks_count") var forksCount: Int?,
                      @ColumnInfo(name = "page_number") var pageNumber: Int?,
                      @Embedded @SerializedName("owner") var user: User?)

