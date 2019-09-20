package br.com.concrete.http;

import java.util.List;
import br.com.concrete.model.RetornoPullRequest;
import br.com.concrete.model.RetornoRepositorio;
import br.com.concrete.util.Constantes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET(Constantes.URL_REPOSITORIES)
    Call<RetornoRepositorio> getRepositorios(@Query("page") String page);

    @GET(Constantes.URL_PULL_REQUESTES)
    Call<List<RetornoPullRequest>> getPullRequests(@Path("login") String login, @Path("name") String name);
}