package com.example.desafioandroid.feature.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.desafioandroid.model.Base;
import com.example.desafioandroid.repositories.BaseRepository;

public class RepositoryViewModel extends ViewModel {

    private LiveData<Base> baseData;
    private BaseRepository baseRepository;

    public RepositoryViewModel(Application application, int currentPage) {
        this.baseRepository = new BaseRepository();
        this.baseData = baseRepository.getBase(currentPage);
    }

    public LiveData<Base> observerBaseRepository(){
        return this.baseData;
    }


}
