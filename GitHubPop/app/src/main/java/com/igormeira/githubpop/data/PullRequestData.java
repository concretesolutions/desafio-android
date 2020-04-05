package com.igormeira.githubpop.data;

import androidx.lifecycle.MutableLiveData;

import com.igormeira.githubpop.apirequests.GitHubClient;
import com.igormeira.githubpop.apirequests.GitHubService;
import com.igormeira.githubpop.model.PullRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestData {

    private ArrayList<PullRequest> pullRequests = new ArrayList<>();
    private MutableLiveData<List<PullRequest>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<PullRequest>> getPullRequestData(String creator, String name) {

        final GitHubService service = new GitHubClient().getGitHubService();

        Call<List<PullRequest>> call = service.getPullRequests(creator, name);
        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> pullRequestsBody = response.body();
                if (pullRequestsBody != null) {
                    pullRequests = (ArrayList<PullRequest>) pullRequestsBody;
                    mutableLiveData.setValue(pullRequests);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}
