package com.pedrenrique.githubapp.mock

import android.content.Context
import android.net.Uri
import com.pedrenrique.githubapp.ext.readAsset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher(private val context: Context) : Dispatcher() {

    companion object {
        const val IMAGE_ASSET = "userimage.png"
        const val REPOSITORIES_ASSET = "repositories_success.json"
        const val PULL_REQUEST_ASSET = "pullrequests_success.json"
    }

    private val responseFilesByPath: Map<String, String> = mapOf(
        "/userimage.png" to IMAGE_ASSET,
        "/search/repositories" to REPOSITORIES_ASSET,
        "/repos/user/repository/pulls" to PULL_REQUEST_ASSET
    )

    private val queue = arrayListOf<String>()

    fun enqueue(asset: String) {
        queue.add(asset)
    }

    override fun dispatch(request: RecordedRequest?): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request?.path).path ?: return errorResponse
        val responseFile = responseFilesByPath[pathWithoutQueryParams]
            ?: queue.takeIf { it.isNotEmpty() }?.removeAt(0)

        return if (responseFile != null) {
            val responseBody = context.readAsset(responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            errorResponse
        }
    }
}