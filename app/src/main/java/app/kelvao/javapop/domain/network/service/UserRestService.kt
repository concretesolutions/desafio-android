package app.kelvao.javapop.domain.network.service

import app.kelvao.javapop.domain.network.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRestService {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<UserResponse>

}