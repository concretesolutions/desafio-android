package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.concrete.challenge.githubjavapop.api.Api;
import com.concrete.challenge.githubjavapop.api.SingleSchedulers;
import com.concrete.challenge.githubjavapop.domain.PullRequest;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class PullRequestViewModel extends ViewModel {
    private MutableLiveData<ArrayList<PullRequest>> pullRequests;
    private MutableLiveData<Throwable> error;
    private Api api;
    private SingleSchedulers singleSchedulers;
    private CompositeDisposable disposable;

    public PullRequestViewModel(Api api, SingleSchedulers singleSchedulers) {
        this.api = api;
        this.singleSchedulers = singleSchedulers;
        disposable = new CompositeDisposable();
        pullRequests = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void loadPullRequests(String userName, String repositoryName, int page) {
        disposable.add(api.getPullRequests(userName, repositoryName, page)
                .compose(singleSchedulers.applySchedulers())
                .subscribe(this::onSuccess, this::onError));
    }

    private <T> void onSuccess(T result) {
        ArrayList<PullRequest> list = (ArrayList<PullRequest>) result;
        ArrayList<PullRequest> temp = pullRequests.getValue();
        if(temp == null) temp = new ArrayList<>();
        temp.addAll(list);
        pullRequests.setValue(temp);
    }

    private void onError(Throwable error) {
        this.error.setValue(error);
    }

    public LiveData<ArrayList<PullRequest>> getPullRequests() {
        return pullRequests;
    }

    public LiveData<Throwable> getError() {
        return error;
    }
}
