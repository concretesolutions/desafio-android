package br.com.victoramaral.githubdive.model.data.remote;

import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.model.pojos.repositories.Repositories;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoriesApi {

    @GET("search/repositories?q=language:Java&sort=stars")
    Observable<Repositories> getAllRepositories(@Query("page") int page);
}
