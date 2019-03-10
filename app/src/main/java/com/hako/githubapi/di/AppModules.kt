package com.hako.githubapi.di

import androidx.room.Room
import com.hako.githubapi.data.repository.database.GithubDatabase
import com.hako.githubapi.data.repository.retrofit.GithubClient
import com.hako.githubapi.data.repository.retrofit.RetrofitDatasource
import com.hako.githubapi.features.repos.RepoListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.concurrent.Executors

val network = module {
    single { GithubClient().getClient() }
    single { RetrofitDatasource() }
}

val database = module {
    single { Room.databaseBuilder(get(), GithubDatabase::class.java, "github_api.db").build() }
    single { get<GithubDatabase>().repositoryDao() }
    single { get<GithubDatabase>().pullrequestDao() }
}

val views = module {
    viewModel { RepoListViewModel(get()) }
}

val threads = module {
    single { Executors.newSingleThreadExecutor() }
}
