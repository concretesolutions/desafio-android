package br.com.concrete.desafio_android.data.external;

import java.util.List;
import br.com.concrete.desafio_android.domain.Pull;
import br.com.concrete.desafio_android.domain.RepositoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Daivid
 * To ilustrate how the Retrofit works
 */
public interface RepositoryApi {

    @GET("search/repositories")
    Call<RepositoryResponse> getRepositories(@Query("q") String language,
                                             @Query("sort") String typeSort,
                                             @Query("page") int pageNumber);

}
