package cess.com.br.androidchallenge.UI.Presenters;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cess.com.br.androidchallenge.Data.Api.RetrofitConfig;
import cess.com.br.androidchallenge.Model.Remote.Repositories;
import cess.com.br.androidchallenge.UI.Contracts.RepoContract;
import cess.com.br.androidchallenge.UI.Views.RepoListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoPresenter implements RepoContract.Presenter {

    private final RepoContract.View mRepoView;

    public RepoPresenter(RepoListView repoListView) {
        this.mRepoView = repoListView;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getFirstPage(String page, String sort, String language) {
        Map<String, String> data = new HashMap<>();
        data.put("q", language);
        data.put("sort", sort);
        data.put("page", page);

        Call<Repositories> call = new RetrofitConfig().githubService().getRepos(data);

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                if (response.isSuccessful())
                    mRepoView.populateList(response.body());
                else
                    mRepoView.showErrorMessage(1);
                
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                mRepoView.showErrorMessage(1);
            }
        });
    }

    @Override
    public void getNextPage(String page, String sort, String language) {
        Map<String, String> data = new HashMap<>();
        data.put("q", language);
        data.put("sort", sort);
        data.put("page", page);

        Call<Repositories> call = new RetrofitConfig().githubService().getRepos(data);

        Log.d("url", String.valueOf(call.request().url()));

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Log.d("teste", String.valueOf(response.body()));
                mRepoView.loadMoreRepos(response.body());

            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
