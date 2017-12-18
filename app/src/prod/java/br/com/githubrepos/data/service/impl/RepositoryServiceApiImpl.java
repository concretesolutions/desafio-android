package br.com.githubrepos.data.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.githubrepos.data.HttpEndpointGenerator;
import br.com.githubrepos.data.endpoint.RepositoryEndpoint;
import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.service.RepositoryServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryServiceApiImpl implements RepositoryServiceApi {

    @Override
    public void search(int page, String language, String sort,
                       final RepositoryServiceCallback<RepositoryStatus> callback) {

        Map<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(page));
        options.put("q", language);
        options.put("sort", sort);

        Call<RepositoryStatus> call = new HttpEndpointGenerator<RepositoryEndpoint>()
                .gen(RepositoryEndpoint.class).list(options);

        call.enqueue(new Callback<RepositoryStatus>() {
            @Override
            public void onResponse(Call<RepositoryStatus> call, Response<RepositoryStatus> response) {
                RepositoryStatus body = response.body();
                if (null == body) {
                    body = new RepositoryStatus(0, false, new ArrayList<Repository>());
                }
                callback.onLoaded(body);
            }

            @Override
            public void onFailure(Call<RepositoryStatus> call, Throwable t) {
            }
        });
    }
}
