package matheusuehara.github.base

import matheusuehara.github.extensions.getJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import java.net.HttpURLConnection

abstract class BaseInstrumentedTest {

    private val mockWebServer: MockWebServer = MockWebServer()

    init {
        mockWebServer.start(8080)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    private fun setResponse(responseJson: String, code: Int) {
        val mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(responseJson.getJson())

        mockWebServer.enqueue(mockResponse)
    }

    fun mockResponse200(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_OK)
    }

    fun mockResponseError404() {
        setResponse("return_error_404", HttpURLConnection.HTTP_NOT_FOUND)
    }
}