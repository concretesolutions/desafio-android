package com.example.github_api_concrete.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.github_api_concrete.model.pojo.repos.Item;
import com.example.github_api_concrete.model.repository.GitHubRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ReposViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listItem = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private GitHubRepository repository = new GitHubRepository();

    public ReposViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getListItem(){
        return this.listItem;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getAllItems(String language, String sort, int page){
        disposable.add(
                repository.getRepositories(language, sort, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> loading.setValue(true))
                .doOnTerminate(() -> loading.setValue(false))
                .subscribe(repositoriesResult -> {
                    listItem.setValue(repositoriesResult.getItems());
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
