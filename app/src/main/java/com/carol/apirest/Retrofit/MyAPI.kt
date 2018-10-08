package com.carol.apirest.Retrofit

import com.carol.apirest.Model.Item
import io.reactivex.Observable
import retrofit2.http.GET

interface MyAPI {

    @get:GET("search/repositories?q=language:Java&sort=stars&page=1")
    var posts: Observable<List<Item>>
}