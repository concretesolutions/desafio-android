package app.kelvao.javapop.network.service

import app.kelvao.javapop.network.response.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRestService {

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<UserResponse>

}