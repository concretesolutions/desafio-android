package com.dobler.desafio_android.api

import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryResponse
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryService
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.data.model.User
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RepositoryServiceTest : BaseServiceTest() {


    protected lateinit var service: GithubRepositoryService
    val serviceSource = GithubRepositoryService::class.java

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(serviceSource)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun repositoryMockTestPage1() {
        enqueueResponse("repositories_page_1.json")

        val results: GithubRepositoryResponse? = service.getPage("language:Java", "stars", 1).execute().body()
        mockWebServer.takeRequest()

        assertThat<List<GithubRepository>>(results?.items, IsNull.notNullValue())
        assertThat(results!!.items[0].name, CoreMatchers.`is`("java-design-patterns"))
        assertThat<User>(results!!.items[0].owner, IsNull.notNullValue())
    }

    @Test
    fun repositoryMockTestPage2() {
        enqueueResponse("repositories_page_2.json")

        val results: GithubRepositoryResponse? = service.getPage("language:Java", "stars", 2).execute().body()
        mockWebServer.takeRequest()

        assertThat<List<GithubRepository>>(results?.items, IsNull.notNullValue())
        assertThat(results!!.items[0].name, CoreMatchers.`is`("druid"))
        assertThat<User>(results!!.items[0].owner, IsNull.notNullValue())
    }


}
