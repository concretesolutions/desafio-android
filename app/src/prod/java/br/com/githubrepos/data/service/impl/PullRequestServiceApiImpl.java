package br.com.githubrepos.data.service.impl;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.githubrepos.data.HttpEndpointGenerator;
import br.com.githubrepos.data.endpoint.PullRequestEndpoint;
import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.data.service.PullRequestServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestServiceApiImpl implements PullRequestServiceApi {

    @Override
    public void list(String ownerLogin, String repoName, final PullRequestCallback<List<PullRequest>> callback) {

        Call<List<PullRequest>> call = new HttpEndpointGenerator<PullRequestEndpoint>()
                .gen(PullRequestEndpoint.class).list(ownerLogin, repoName);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> body = response.body();
                if (null == body) {
                    body = new ArrayList<>();
                }
                callback.onLoaded(body);
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Crashlytics.logException(t);

                try {
                    Response<List<PullRequest>> execute = call.execute();
                    String errorMessage = execute.message();
                    callback.onError(errorMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
