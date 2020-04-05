package com.igormeira.githubpop.pullrequest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.igormeira.githubpop.data.PullRequestData;
import com.igormeira.githubpop.model.PullRequest;

import java.util.List;

public class PullRequestViewModel extends AndroidViewModel {

    private PullRequestData pullRequestData;

    public PullRequestViewModel(@NonNull Application application) {
        super(application);
        pullRequestData = new PullRequestData();
    }

    public LiveData<List<PullRequest>> getAllPullRequests(String creator, String name) {
        return pullRequestData.getPullRequestData(creator, name);
    }
}
