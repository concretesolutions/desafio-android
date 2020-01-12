package com.example.github_api_concrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.github_api_concrete.model.pojo.pullrequests.Response;
import com.example.github_api_concrete.model.repository.GitHubRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PullRequestsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Response>> listPR = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private GitHubRepository repository = new GitHubRepository();

    public PullRequestsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Response>> getListPR() {
        return this.listPR;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getAllPR(String owner, String repo){
        disposable.add(
                repository.getPRs(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> loading.setValue(true))
                .doOnTerminate(() -> loading.setValue(false))
                .subscribe(pullRequestResults -> {
                    listPR.setValue(pullRequestResults);
                }, throwable -> {
                    Log.i("Log", "Error" + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
