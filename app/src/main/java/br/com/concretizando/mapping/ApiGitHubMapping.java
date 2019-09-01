package br.com.concretizando.mapping;

import java.util.List;

import br.com.concretizando.constant.ApiGitHubCredential;
import br.com.concretizando.model.PullRequest;
import br.com.concretizando.model.ReposForStar;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiGitHubMapping {

    @GET(ApiGitHubCredential.ENDPOINT_1)
    Call<ReposForStar> reposForStar(@Query(ApiGitHubCredential.ENPOINT_1_PARAM_1) String q,
                                   @Query(ApiGitHubCredential.ENPOINT_1_PARAM_2) String sort,
                                   @Query(ApiGitHubCredential.ENPOINT_1_PARAM_3) String page);

    @GET(ApiGitHubCredential.ENDPOINT_2)
    Call<List<PullRequest>> pullRequestsForRepo(
            @Path(ApiGitHubCredential.ENDPOINT_2_PATH_1) String user,
            @Path(ApiGitHubCredential.ENDPOINT_2_PATH_2) String repository);
}
