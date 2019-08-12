package com.example.desafioandroid.feature.pulls;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.desafioandroid.model.Pull;
import com.example.desafioandroid.repositories.PullRepository;

import java.util.List;

public class PullViewModel extends ViewModel {

    private LiveData<List<Pull>> baseData;
    private PullRepository pullRepository;

    public PullViewModel(Application application, String user, String repository) {
        this.pullRepository = new PullRepository();
        this.baseData = pullRepository.getPulls(user, repository);
    }

    public LiveData<List<Pull>> observerPullRepository(){
        return this.baseData;
    }
}
