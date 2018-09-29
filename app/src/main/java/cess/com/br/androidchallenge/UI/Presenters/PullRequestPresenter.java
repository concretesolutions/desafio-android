package cess.com.br.androidchallenge.UI.Presenters;

import android.util.Log;

import java.util.ArrayList;

import cess.com.br.androidchallenge.Data.Api.RetrofitConfig;
import cess.com.br.androidchallenge.Model.Remote.PR;
import cess.com.br.androidchallenge.UI.Contracts.PullRequestContract;
import cess.com.br.androidchallenge.UI.Views.PullRequestView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestPresenter implements PullRequestContract.Presenter {

    private final PullRequestContract.View mPrView;
    private Call<ArrayList<PR>> call;

    public PullRequestPresenter(PullRequestView pullRequestView) {
        mPrView = pullRequestView;
    }

    @Override
    public void getPullRequests(String userName, final String repoName) {

        call = new RetrofitConfig().githubService().getPRs(userName, repoName);

        call.enqueue(new Callback<ArrayList<PR>>() {
            @Override
            public void onResponse(Call<ArrayList<PR>> call, Response<ArrayList<PR>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null)
                        if (response.body().size() > 0)
                            mPrView.populateList(response.body());
                        else
                            mPrView.showErrorMessage(2);
                } else {
                    mPrView.showErrorMessage(1);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<PR>> call, Throwable t) {
                mPrView.showErrorMessage(1);
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        call.cancel();
    }
}
