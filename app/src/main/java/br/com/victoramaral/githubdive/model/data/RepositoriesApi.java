package br.com.victoramaral.githubdive.model.data;

import br.com.victoramaral.githubdive.model.pojos.Repositories;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoriesApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    Observable<Repositories> getAllRepositories(@Query("page") int page);
}
