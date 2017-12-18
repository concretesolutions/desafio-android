package br.com.githubrepos.data.endpoint;

import java.util.Map;

import br.com.githubrepos.data.entity.RepositoryStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RepositoryEndpoint {

    //Example: https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1

    //@GET("search/repositories")
    //Observable<RepositoryStatus> list(@QueryMap Map<String, String> options);

    @GET("search/repositories")
    Call<RepositoryStatus> list(@QueryMap Map<String, String> options);
}
