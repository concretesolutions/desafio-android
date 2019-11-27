package com.ruiderson.desafio_android

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ruiderson.desafio_android.api.ApiGithub
import com.ruiderson.desafio_android.api.RetrofitInitializer
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiGithubTest{

    private lateinit var apiService: ApiGithub

    @Before
    fun init(){
        apiService = RetrofitInitializer().githubService()
    }


    @Test
    fun test_repository_api(){
        val call = apiService.getRepository("language:Java","stars", 1)
        val response = call.execute()
        assertEquals(response.code(), 200)
        assert(response.body() != null)

        response.body()?.let {
            val repositories = it.items
            assert(repositories.count() > 0)
        }
    }


    @Test
    fun test_pull_api(){
        val pullUser = "google"
        val repositoryName = "guava"

        val call = apiService.getPulls(pullUser,repositoryName)
        val response = call.execute()
        assertEquals(response.code(), 200)
        assert(response.body() != null)

        response.body()?.let {
            val pulls = it
            assert(pulls.count() > 0)
        }
    }

}