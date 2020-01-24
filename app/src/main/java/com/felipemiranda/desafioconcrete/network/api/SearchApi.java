package com.felipemiranda.desafioconcrete.network.api;


import com.felipemiranda.desafioconcrete.model.ItemDetail;
import com.felipemiranda.desafioconcrete.model.response.SearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by felipemiranda
 */

public interface SearchApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<SearchResponse> requestSearch(@Query("page") int page);

    @GET
    Call<ArrayList<ItemDetail>> requestItemDetail(@Url String url);
}
