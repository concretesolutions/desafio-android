package com.danielmaia.desafioandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.desafioandroid.model.Pull;
import com.danielmaia.desafioandroid.repository.PullRepository;

import java.util.List;

public class PullActivityViewModel extends AndroidViewModel {

    private PullRepository pullRepository;
    private LiveData<List<Pull>> pullLiveData;
    private MutableLiveData<Boolean> isUpdating =  new MutableLiveData<>();
    private MutableLiveData<Integer> openedCount;
    private MutableLiveData<Integer> closedCount;

    public PullActivityViewModel(@NonNull Application application) {
        super(application);
        pullRepository = new PullRepository();
    }

    public LiveData<List<Pull>> getListRepos(String owner, String repo){
        this.pullLiveData = pullRepository.getReposOnServer(owner, repo);
        isUpdating.setValue(true);
        return this.pullLiveData;
    }

    public MutableLiveData<Integer> getOpenedCount(String repo) {
        this.openedCount = pullRepository.getOpenedCount(repo);
        return openedCount;
    }

    public MutableLiveData<Integer> getClosedCount(String repo) {
        this.closedCount = pullRepository.getClosedCount(repo);
        return closedCount;
    }

}