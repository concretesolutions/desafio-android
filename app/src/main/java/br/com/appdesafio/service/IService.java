package br.com.appdesafio.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IService {
    /**
     *service responsible for logging in to the web service.
     * @param contentType
     * @return
     */


    /**
     * service responsible for searching the list of dogs in the web service.
     * @param authorization
     * @param category
     * @return
     */
    @GET("/feed")
    Call<JsonObject> getListRepository(@Header("Authorization") String authorization, @Query("category") String category);
}
