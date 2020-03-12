package br.com.ribamar.concrete.model.data.remote;



import java.util.List;

import br.com.ribamar.concrete.model.pojos.repositories.Repositories;
import br.com.ribamar.concrete.model.pojos.requests.Request;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoriesApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    Observable<Repositories> getAllRepositories(@Query("page") int page,
                                                @Query("per_page") int perPage);

    @GET("repos/{owner}/{repo}/pulls")
    Observable<List<Request>> getAllPullRequests(@Path("owner") String owner,
                                                 @Path("repo") String repository);
}
