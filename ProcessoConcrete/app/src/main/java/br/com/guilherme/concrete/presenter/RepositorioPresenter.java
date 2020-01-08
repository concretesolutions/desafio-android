package br.com.guilherme.concrete.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.guilherme.concrete.model.Repositorio;
import br.com.guilherme.concrete.model.RepositorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioPresenter {
    private View view;
    private RepositorioService service;

    public RepositorioPresenter (View view){
        this.view = view;
        service = new RepositorioService();
    }

    public void getAllRepositorios(int pagLoad){
        service.getAllRepositorios(String.valueOf(pagLoad))
                .enqueue(new Callback<Repositorio>() {
                    @Override
                    public void onResponse(Call<Repositorio> call, Response<Repositorio> response) {
                        List<Repositorio> repositorios = new ArrayList<>();
                        if (response.body() != null)
                            repositorios.addAll(response.body().getRepositorios());
                        view.setRecyclerView(repositorios);
                    }

                    @Override
                    public void onFailure(Call<Repositorio> call, Throwable t) {
                        view.onError(t.getMessage());
                        Log.e("api_erro", t.getMessage());
                    }
                });
    }

    public interface View{
        void setRecyclerView(List<Repositorio> repositorios);
        void onError(String errorException);
    }
}
