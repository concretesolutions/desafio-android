package com.hako.githubapi.di

import androidx.room.Room
import com.hako.githubapi.data.database.GithubDatabase
import com.hako.githubapi.data.retrofit.GithubClient
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.features.repos.RepoListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.concurrent.Executors

val network = module {
    single { GithubClient().getClient() }
    single { RemoteDatasource() }
}

val database = module {
    single { Room.databaseBuilder(get(), GithubDatabase::class.java, "github_api.db").build() }
    single { get<GithubDatabase>().repositoryDao() }
}

val views = module {
    viewModel { RepoListViewModel(get()) }
}

val threads = module {
    single { Executors.newSingleThreadExecutor() }
}
