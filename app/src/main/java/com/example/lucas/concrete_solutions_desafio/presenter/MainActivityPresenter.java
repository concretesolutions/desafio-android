package com.example.lucas.concrete_solutions_desafio.presenter;

import android.content.Context;

import com.example.lucas.concrete_solutions_desafio.contract.MainContract;
import com.example.lucas.concrete_solutions_desafio.dao.ApiDao;
import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;
import com.example.lucas.concrete_solutions_desafio.view.MainActivity;

public class MainActivityPresenter implements MainContract.Presenter{
    ApiDao apiDao = new ApiDao(this);
    MainContract.View mainActivity;

    public MainActivityPresenter(MainContract.View mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void repositoryRequisition (int pageNumber, final Context context){
        apiDao.repositoryRequisition(pageNumber, context);
    }

    @Override
    public void fillScreenPresenter (RepositoryList repositories){
        mainActivity.fillScreen(repositories);
    }

    @Override
    public void refillScreenPresenter(RepositoryList repositories) {
        mainActivity.refillScreen(repositories);
    }

    @Override
    public void interruptProgressBar(){
        mainActivity.interruptProgressBar();
    }
}
