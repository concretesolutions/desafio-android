package com.example.githubtest.di

import com.example.githubtest.data.request.RepositoryRequest
import com.example.githubtest.data.request.RepositoryContract
import org.koin.dsl.module

val repositoryModule = module {
    factory { RepositoryRequest(get()) as RepositoryContract }
}