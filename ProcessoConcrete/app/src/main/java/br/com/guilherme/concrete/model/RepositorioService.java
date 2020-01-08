package br.com.guilherme.concrete.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioService {

    private static final String URL_REQUEST = "https://api.github.com/";
    private RepositoriosAPI api;

    public RepositorioService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_REQUEST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(RepositoriosAPI.class);
    }

    public Call<Repositorio> getAllRepositorios(String pagLoad){
        return api.getAllRepositorios("Java", "stars", pagLoad);
    }

    public Call<List<PullRequest>>getAllPulls(String nomeUser, String nomeRepo){
        return api.getAllPulls(nomeUser, nomeRepo);
    }
}
