package br.com.desafioandroid.wsconsumer;

import java.util.List;

import br.com.desafioandroid.model.PullsRequests;
import br.com.desafioandroid.wsconsumer.responses.ResponsePulls;
import br.com.desafioandroid.wsconsumer.responses.ResponseRepositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/repositories")
    Call<ResponseRepositories> getAllRepositories(@Query("q") String language,
                                                  @Query("sort") String sort,
                                                  @Query("page") int page);

    @GET("repos/{name}/{repo}/pulls")
    Call<List<PullsRequests>> getPulls(@Path("name") String name,
                                       @Path("repo") String repo);

}
