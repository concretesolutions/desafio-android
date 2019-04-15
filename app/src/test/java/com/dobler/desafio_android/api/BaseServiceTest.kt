package com.dobler.desafio_android.api

//import okhttp3.mockwebserver.MockResponse
//import okhttp3.mockwebserver.MockWebServer
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryResponse
import com.dobler.desafio_android.data.api.githubRepository.GithubRepositoryService
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.data.model.RepositoryPullRequest
import com.dobler.desafio_android.data.model.User
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
open class BaseServiceTest{
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    protected lateinit var mockWebServer: MockWebServer


      fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
