package com.desafioandroid.feature.home.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.desafioandroid.core.base.BaseViewModel
import com.desafioandroid.core.helper.Resource
import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.feature.home.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    val getList = MutableLiveData<Resource<MutableList<Item>>>()

    fun fetchList(page: Int = 1) {
        viewModelScope.launch {
            getList.loading(true)
            try {
                getList.success(repository.getList(page)?.let { it })
            } catch (e: Exception) {
                getList.error(e)
            } finally {
                getList.loading(false)
            }
        }
    }
}