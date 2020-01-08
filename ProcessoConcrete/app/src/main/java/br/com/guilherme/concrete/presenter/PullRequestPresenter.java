package br.com.guilherme.concrete.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.guilherme.concrete.model.PullRequest;
import br.com.guilherme.concrete.model.RepositorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestPresenter {
    private View view;
    private RepositorioService service;

    public PullRequestPresenter(View view) {
        this.view = view;
        service = new RepositorioService();
    }

    public void getAllPulls() {
        service.getAllPulls()
                .enqueue(new Callback<List<PullRequest>>() {
                    @Override
                    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                        List<PullRequest> pullRequests = new ArrayList<>();
                        if (response.body() != null)
                            pullRequests.addAll(response.body());
                        view.setRecyclerView(pullRequests);
                    }

                    @Override
                    public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                        view.onError(t.getMessage());
                        Log.e("api_erro", t.getMessage());
                    }
                });
    }

    public interface View{
        void setRecyclerView(List<PullRequest> repositorios);
        void onError(String errorException);
    }
}
