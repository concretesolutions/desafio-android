package com.marcos.desafioandroidconcrete.api;

import com.marcos.desafioandroidconcrete.domain.PullRequest;
import com.marcos.desafioandroidconcrete.domain.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marco on 30,Janeiro,2020
 */
public interface RepositoryApi {


    @GET("search/repositories?q=language:Java&sort=stars")
    Call<Repository> getRepositories( @Header("Authorization") String authKey,@Query("page") Integer page);

    @GET("repos/{OwnerUsername}/{NameRepository}/pulls")
    Call<List<PullRequest>> getPullRequest( @Header("Authorization") String authKey,@Path("OwnerUsername") String ownerName, @Path("NameRepository") String repositoryName);

}
