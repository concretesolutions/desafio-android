package com.concrete.android.challenge.data.remote;

import com.concrete.android.challenge.data.model.PullRequest;
import com.concrete.android.challenge.data.remote.response.RepositoryResponse;
import io.reactivex.Single;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface GithubService {

  @GET("search/repositories")
  Single<Result<RepositoryResponse>> getRepositories(@NotNull @Query("q") String query,
      @Query("sort") String sort, @Query("page") int page);

  @GET("repos/{userName}/{projectName}/pulls")
  Single<Result<List<PullRequest>>> getPullRequests(@Path("userName") String userName,
      @Path("projectName") String projectName, @Query("state") String state,
      @Query("page") int page);
}
