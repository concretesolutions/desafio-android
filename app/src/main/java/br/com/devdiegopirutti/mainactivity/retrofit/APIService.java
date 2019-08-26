package br.com.devdiegopirutti.mainactivity.retrofit;


import java.util.List;

import br.com.devdiegopirutti.mainactivity.models.BasePRResponse;
import br.com.devdiegopirutti.mainactivity.models.BaseResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("search/repositories")
    Call<BaseResponse> obterListaUsuarios(@Query("page") int page,
                                          @Query("sort") String sort, @Query("q") String language);

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<BasePRResponse>> obterRepositorios(@Path("owner") String user, @Path("repo") String repos);
}

