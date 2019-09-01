package br.com.concretizando.dao;

import java.util.List;

import br.com.concretizando.constant.ApiGitHubCredential;
import br.com.concretizando.mapping.ApiGitHubMapping;
import br.com.concretizando.model.PullRequest;
import br.com.concretizando.model.ReposForStar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGitHubDao {

    private ApiGitHubMapping mapping;

    public ApiGitHubDao() {

        this.initClient();
    }

    private void initClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ApiGitHubCredential.URL_BASE)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        this.mapping = retrofit.create(ApiGitHubMapping.class);
    }

    public Call<ReposForStar> reposForStar(String q, String sort, String page) {

        Call<ReposForStar> call = this.mapping.reposForStar(q, sort, page);
        return call;
    }

    public Call<List<PullRequest>> pullRequestsForRepo(String user, String repository) {

        Call<List<PullRequest>> call = this.mapping.pullRequestsForRepo(user, repository);
        return call;
    }
}














