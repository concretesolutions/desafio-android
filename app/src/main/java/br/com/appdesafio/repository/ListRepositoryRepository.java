package br.com.appdesafio.repository;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import br.com.appdesafio.model.persistence.SharedPreference;
import br.com.appdesafio.service.IService;


public class ListRepositoryRepository {

    public IService mIservice;
    public SharedPreference sharedPreferences;
    public Context mContext;

    @Inject
    public ListRepositoryRepository(final  IService service, final SharedPreference preference, final Application application){
        this.mIservice = service;
        this.sharedPreferences = preference;
        this.mContext = application;

    }


}
