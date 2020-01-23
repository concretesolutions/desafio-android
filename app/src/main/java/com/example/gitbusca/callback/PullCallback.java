package com.example.gitbusca.callback;

import android.util.Log;

import com.example.gitbusca.activity.PullActivity;
import com.example.gitbusca.model.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullCallback implements Callback<List<Pull>> {
    private PullActivity activity;

    public PullCallback(PullActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
        if( !response.isSuccessful() ) {
            Log.i("Log", "Erro: " + response.message() + " Code: " + response.code() );
        }
        else {
            List<Pull> pulls = response.body();
            for (Pull p : pulls){
                activity.inserirListaPull(p.getTitle(), p.getNumber(), p.getBody(), p.getUser());
            }

            activity.carregarRecycle();
        }
    }

    @Override
    public void onFailure(Call<List<Pull>> call, Throwable t) {
        Log.i("Log", "Erro PullCallback Failure: " + t.getMessage());
    }
}
