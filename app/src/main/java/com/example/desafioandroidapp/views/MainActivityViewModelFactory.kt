package com.example.desafioandroidapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioandroidapp.data.*

class MainActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val desafioApiDatasource = DesafioApiDataSource()
        val desafioApiRepository = DesafioApiRepository(desafioApiDatasource)
        return MainActivityViewModel(desafioApiRepository) as T
    }
}