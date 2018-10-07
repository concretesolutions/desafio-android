package com.concrete.andresdavid.desafioandroid.repository

import com.concrete.andresdavid.desafioandroid.model.PullRequest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import io.reactivex.Observable

class PullRequestRepositoryTest {
    private val repositoryFullName: String = "iluwatar/java-design-patterns"
    private lateinit var repository: PullRequestRepository


    @Before
    fun setUp() {
        repository = PullRequestRepository()
    }

    @Test
    fun shoudGetObservable() {
        val response = repository.getPullRequestsByRepository(repositoryFullName)
        Assert.assertTrue(response is Observable<List<PullRequest>>)
    }
}