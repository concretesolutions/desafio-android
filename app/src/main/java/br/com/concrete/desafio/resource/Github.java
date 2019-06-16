package br.com.concrete.desafio.resource;

import br.com.concrete.desafio.model.SearchRepositoryResult;
import br.com.concrete.desafio.model.PullRequest;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Github {
    @GET("repositories")
    Single<SearchRepositoryResult> getRepositories(
            @Query("q") String q,
            @Query("sort") String sort,
            @Query("page") String page
    );

    @GET("pulls")
    Single<PullRequest[]> getPullRequests(
            @Query("page") String page
    );
}
