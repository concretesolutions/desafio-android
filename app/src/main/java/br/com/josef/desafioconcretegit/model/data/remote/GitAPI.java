package br.com.josef.desafioconcretegit.model.data.remote;


import java.util.List;

import br.com.josef.desafioconcretegit.model.pojo.pull.PullRequest;
import br.com.josef.desafioconcretegit.model.pojo.repositories.GitResult;
import io.reactivex.Observable;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitAPI {

    @GET("search/repositories?q=language:Java&sort=stars")
    Observable<GitResult> getAllRepositorios(@Query("page") int page);

    @GET("repos/{creator}/{repository}/pulls")
    Observable<List<PullRequest>> getPullRequests(@Path("creator") String creatorString,
                                                  @Path("repository") String repoString);

}
