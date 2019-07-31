package matheusuehara.github.di

import matheusuehara.github.features.pullrequests.PullRequestViewModel
import matheusuehara.github.features.repository.RepositoryViewModel
import matheusuehara.github.repository.GitHubRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    single{ GitHubRepositoryImpl() }
    viewModel{ RepositoryViewModel(get()) }
    viewModel{ PullRequestViewModel(get()) }
}
