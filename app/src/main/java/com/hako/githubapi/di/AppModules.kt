package com.hako.githubapi.di

import androidx.room.Room
import com.hako.githubapi.data.database.GithubDatabase
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.data.retrofit.github.GithubClient
import com.hako.githubapi.domain.usecases.DeleteRepositories
import com.hako.githubapi.domain.usecases.GetPullRequests
import com.hako.githubapi.domain.usecases.GetRepositories
import com.hako.githubapi.features.pullRequest.PullListViewModel
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

val usecases = module {
    single { GetRepositories(get()) }
    single { DeleteRepositories(get()) }
    single { GetPullRequests() }
}

val threads = module {
    single { Executors.newSingleThreadExecutor() }
}

val views = module {
    viewModel { RepoListViewModel() }
    viewModel { PullListViewModel() }
}
