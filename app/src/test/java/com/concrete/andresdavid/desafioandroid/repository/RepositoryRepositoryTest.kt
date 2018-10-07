package com.concrete.andresdavid.desafioandroid.repository

import com.concrete.andresdavid.desafioandroid.model.Repository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import io.reactivex.Observable

class RepositoryRepositoryTest {
    private val page: Int = 1
    private lateinit var repository: RepositoryRepository

    @Before
    fun setUp() {
        repository = RepositoryRepository()
    }

    @Test
    fun shoudGetObservable() {
        val response = repository.searchJavaRepositories(1)
        Assert.assertTrue(response is Observable<List<Repository>>)
    }
}