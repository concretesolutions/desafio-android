package br.com.githubjavapop.service;

import br.com.githubjavapop.entidade.ListaRepositorio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListaRepositorioService {
    @GET("/search/repositories")
    Call<ListaRepositorio> getListaRepositorio(@Query("q") String q, @Query("sort") String sort,
                                          @Query("page") int page);
}