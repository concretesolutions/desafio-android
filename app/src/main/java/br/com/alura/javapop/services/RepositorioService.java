package br.com.alura.javapop.services;

import java.util.List;

import br.com.alura.javapop.dto.RepositorioSinc;
import br.com.alura.javapop.model.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositorioService {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositorioSinc> listaRepositorios(@Query("page") int pagina);

    @GET("repos/{usuario}/{repositorio}/pulls")
    Call<List<PullRequest>>  listaPullRequest(@Path("usuario") String usuario, @Path("repositorio") String repositorio);
}
