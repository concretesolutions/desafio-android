package com.example.githubtest.di

import com.example.githubtest.features.pullrequest.PullRequestViewModel
import com.example.githubtest.features.repository.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { RepositoryViewModel(get()) }
    viewModel { PullRequestViewModel(get()) }
}