package com.losingtimeapps.domainTest

import com.losingtimeapps.domain.entity.Repository
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class MockBackendError<T> {

    val notFoundResponse = Single.error<T>(
        HttpException(
            Response.error<T>(
                404, ResponseBody.create(
                    MediaType.parse("application/json"),
                    ""
                )
            )
        )
    )

    val invalidLanguageResponse = Single.error<T>(
        HttpException(
            Response.error<T>(
                422, ResponseBody.create(
                    MediaType.parse("application/json"),
                    ""
                )
            )
        )
    )


    val internalServerErrorResponse = Single.error<T>(
        HttpException(
            Response.error<T>(
                500, ResponseBody.create(
                    MediaType.parse("application/json"),
                    ""
                )
            )
        )
    )

    val networkConnectionErrorResponse = Single.error<T>(
        UnknownHostException("Network Connection Error")
    )

}