package com.desafioandroid.feature.home.repository

import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.data.source.remote.ApiService

class HomeRepository(private val apiService: ApiService) {

    suspend fun getList(page: Int) : MutableList<Item>?{
        val homeResponse = apiService.getListHome(page)
        return homeResponse.items.toMutableList()
    }
}