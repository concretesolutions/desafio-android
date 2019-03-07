package com.example.lucasdias.mvvmpoc.di

import android.arch.persistence.room.Room
import com.example.lucasdias.mvvmpoc.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "appDatabase.db")
                .build()
    }

    single { get<AppDatabase>().repositoryDao()}
    single{ get<AppDatabase>().pullRequestDao()}
}