package br.com.guilherme.concrete.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoriosAPI {

    @GET("search/repositories")
    Call<Repositorio> getAllRepositorios(@Query("q") String linguagem,
                                               @Query("sort") String sortName,
                                               @Query("page") String pageNumber);

    @GET("repos/{nomeUser}/{nomeRepo}/pulls")
    Call<Repositorio> getAllPulls();

}
