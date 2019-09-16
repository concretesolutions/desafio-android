package com.danielmaia.desafioandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.danielmaia.desafioandroid.model.Repo;
import com.danielmaia.desafioandroid.repository.RepoRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private RepoRepository repoRepository;
    private LiveData<List<Repo>> repoLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repoRepository = new RepoRepository();
    }

    public LiveData<List<Repo>> getListRepos(int page){
        this.repoLiveData = repoRepository.getReposOnServer(page);
        return this.repoLiveData;
    }
}
