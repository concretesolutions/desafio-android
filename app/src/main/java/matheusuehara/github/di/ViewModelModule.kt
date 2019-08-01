package matheusuehara.github.di

import matheusuehara.github.features.pullrequests.PullRequestViewModel
import matheusuehara.github.features.repository.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { RepositoryViewModel(get()) }
    viewModel { PullRequestViewModel(get()) }
}
