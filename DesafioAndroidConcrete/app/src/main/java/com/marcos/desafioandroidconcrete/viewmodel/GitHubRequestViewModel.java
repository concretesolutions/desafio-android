package com.marcos.desafioandroidconcrete.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcos.desafioandroidconcrete.domain.AcessToken;
import com.marcos.desafioandroidconcrete.request.GithubClientRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marco on 19,Abril,2020
 */
public class GitHubRequestViewModel extends ViewModel {
    public MutableLiveData<AcessToken> gitAcessTokenMutableLiveData = new MutableLiveData<>();


    public void getAccessToken(String clientId,
                               String clientSecret,
                               String code) {
        GithubClientRequest.getInstance().getAcessToken(clientId, clientSecret, code).enqueue(new Callback<AcessToken>() {
            @Override
            public void onResponse(Call<AcessToken> call, Response<AcessToken> response) {
                if (response.isSuccessful()) {
                    gitAcessTokenMutableLiveData.setValue(response.body());
                } else {
                    gitAcessTokenMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AcessToken> call, Throwable t) {
                gitAcessTokenMutableLiveData.setValue(null);
            }
        });
    }

}
