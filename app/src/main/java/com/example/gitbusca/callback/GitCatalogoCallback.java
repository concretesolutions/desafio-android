package com.example.gitbusca.callback;

import android.util.Log;

import com.example.gitbusca.activity.MainActivity;
import com.example.gitbusca.model.Git;
import com.example.gitbusca.model.GitCatalogo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitCatalogoCallback implements Callback<GitCatalogo> {
    private MainActivity mainActivity;

    public GitCatalogoCallback(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onResponse(Call<GitCatalogo> call, Response<GitCatalogo> response) {
        if( !response.isSuccessful() ){
            Log.i("Log", "Erro: " + response.message() + " Code: " + response.code());
        } else {
            GitCatalogo catalogo = response.body();
            for (Git git : catalogo.getItems() ){

                mainActivity.inserirListaRepositorio(
                        git.getId(),
                        git.getName(),
                        git.getDescription(),
                        git.getOwner().getLogin(),
                        git.getFull_name(),
                        git.getStargazers_count(),
                        git.getForks_count(),
                        git.getOwner().getAvatar_url());
            }

            mainActivity.carregarRecycle();
        }
    }

    @Override
    public void onFailure(Call<GitCatalogo> call, Throwable t) {
        Log.i("Log", "Erro GitCatalogo Failure: " + t.getMessage());
    }

}
