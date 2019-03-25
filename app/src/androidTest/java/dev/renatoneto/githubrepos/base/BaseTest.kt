package dev.renatoneto.githubrepos.base

import android.content.Context
import dev.renatoneto.githubrepos.di.AppModule
import dev.renatoneto.githubrepos.util.MockUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
abstract class BaseTest {

    lateinit var mockServer: MockWebServer

    init {
        AppModule.apiUrl = "http://localhost:8080/"
    }

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    protected fun enqueueMockServer(context: Context, responseBodyFile: String, responseCode: Int = 200) {
        val mockedResponse = MockResponse()
            .setResponseCode(responseCode)
            .setBody(MockUtils.readFileFromAssets(context, responseBodyFile))

        mockServer.enqueue(mockedResponse)
    }

}
