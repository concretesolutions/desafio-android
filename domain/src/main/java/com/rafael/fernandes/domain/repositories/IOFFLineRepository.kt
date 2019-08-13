package com.rafael.fernandes.domain.repositories

import com.rafael.fernandes.domain.model.Item
import io.reactivex.Observable
import io.reactivex.Single

interface IOFFLineRepository {
    fun save(item: Item): Long
    fun retrieveAllRepositoriesSaved(): Observable<MutableList<Item>>
    fun getRepositoryById(id: Int): Single<Item>
}