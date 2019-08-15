package br.com.desafioandroid.wsconsumer;

import br.com.desafioandroid.wsconsumer.responses.ResponseRepositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("repositories")
    Call<ResponseRepositories> getAllRepositories(@Query("q") String language,
                                                  @Query("sort") String sort,
                                                  @Query("page") int page);

}
