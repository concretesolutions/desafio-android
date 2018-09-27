package br.com.alura.javapop.services;

import br.com.alura.javapop.dto.RepositorioSinc;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositorioService {

    @GET("repositories?q=language:Java&sort=stars")
    Call<RepositorioSinc> lista(@Query("page") int pagina);
}
