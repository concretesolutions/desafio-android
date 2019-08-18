package dev.theuzfaleiro.trendingongithub.ui.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.theuzfaleiro.trendingongithub.ui.feature.home.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModelFactory @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(homeRepository) as T
}