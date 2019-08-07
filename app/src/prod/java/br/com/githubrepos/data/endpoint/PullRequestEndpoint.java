package br.com.githubrepos.data.endpoint;

import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PullRequestEndpoint {

    @GET("/repos/{owner}/{repository}/pulls")
    Call<List<PullRequest>> list(@Path("owner") String owner, @Path("repository") String repository);
}
