package com.example.rpanaquecavana.gitandroid;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detalle;
import com.example.rpanaquecavana.gitandroid.Modelos.RepoGit;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService
{
     String API_ROUTE = "search/repositories";

     @GET(API_ROUTE)
     Call<RepoGit> getRepositorio(
             @Query("q") String q ,
             @Query("sort") String sort,
             @Query("page") int page);

     String API_DETALLE = "repos/{autor}/{repo}/pulls";

     @GET(API_DETALLE)
     Call<DetailBody> getDetalle(@Path("autor") String autor, @Path("repo") String repositorio);


}
