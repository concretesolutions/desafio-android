package com.igormeira.githubpop.repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.igormeira.githubpop.R;
import com.igormeira.githubpop.databinding.ItemRepositoryBinding;
import com.igormeira.githubpop.model.PullRequest;
import com.igormeira.githubpop.model.Repository;
import com.igormeira.githubpop.model.RepositoryResponse;
import com.igormeira.githubpop.pullrequest.PullRequestsActivity;

import java.util.List;

public class RepositoriesViewModel extends AndroidViewModel {

    private Context context;
    private RepositoryResponse repositoryResponse;
    private MutableLiveData<Intent> intent;

    public RepositoriesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        repositoryResponse = new RepositoryResponse();
        intent = new MutableLiveData<>();
    }

    public LiveData<List<Repository>> getAllRepositories() {
        return repositoryResponse.getMutableLiveData();
    }

    public void callPullRequests(Repository repository){
        Log.e("CLICK: ", "FOI");
    }

}
