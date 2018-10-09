package br.com.repository.app

import br.com.repository.viewmodel.PullRequestViewModel
import br.com.repository.viewmodel.RepositoryViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { RepositoryViewModel()}
    viewModel { PullRequestViewModel() }
}
