package com.challenge.accenture.interfaces;

import com.challenge.accenture.objects.Pull;
import com.challenge.accenture.objects.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/*
 * Created by Cezhar Arevalo on 10/21/18.
 */
public interface RestClient {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<Repository> getRepo(@Query("page") int page);

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<Pull>> getPulls(@Path("owner") String owner, @Path("repo") String repo);

}
