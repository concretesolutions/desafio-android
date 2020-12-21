package com.ccortez.desafioconcrete.ui.main

import androidx.lifecycle.ViewModel
import com.ccortez.desafioconcrete.data.repository.ItemsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val itemsRepository: ItemsRepository) : ViewModel() {

    val repositoryList by lazy {
        itemsRepository.observePagedUsers()
    }

}