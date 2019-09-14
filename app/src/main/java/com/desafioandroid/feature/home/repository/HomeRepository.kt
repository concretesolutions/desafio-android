package com.desafioandroid.feature.home.repository

import com.desafioandroid.data.model.home.entity.Item
import com.desafioandroid.data.source.remote.ApiService

class HomeRepository(private val client: ApiService) {

    suspend fun getList(page: Int) : MutableList<Item>?{
        val movieResponse = client.getListHome(page)
        return movieResponse.items.toMutableList()
    }
}