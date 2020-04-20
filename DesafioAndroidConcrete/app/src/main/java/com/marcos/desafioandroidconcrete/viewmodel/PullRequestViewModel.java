package com.marcos.desafioandroidconcrete.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcos.desafioandroidconcrete.domain.PullRequest;
import com.marcos.desafioandroidconcrete.request.RepositoryRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marco on 19,Abril,2020
 */
public class PullRequestViewModel extends ViewModel {
    public MutableLiveData<List<PullRequest>> pullRequestMutableLiveData = new MutableLiveData<>();

    public List<PullRequest> getPullRequestCache() {
        return pullRequestMutableLiveData.getValue();
    }

    public void getPullRequest(String token, String ownerName, String repositoryName) {
        RepositoryRequest.getInstance().fetchPullRequest(token, ownerName, repositoryName).enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    pullRequestMutableLiveData.setValue(response.body());
                } else {
                    pullRequestMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                pullRequestMutableLiveData.setValue(null);
            }
        });
    }

}
