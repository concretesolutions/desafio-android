package com.example.lucas.concrete_solutions_desafio.presenter;

import android.content.Context;

import com.example.lucas.concrete_solutions_desafio.contract.PullRequestContract;
import com.example.lucas.concrete_solutions_desafio.dao.ApiDao;
import com.example.lucas.concrete_solutions_desafio.model.PullRequestList;
import com.example.lucas.concrete_solutions_desafio.view.PullRequestActivity;

public class PullRequestPresenter implements PullRequestContract.Presenter{
    ApiDao apiDao = new ApiDao(this);
    PullRequestContract.View pullRequestActivity;

    public PullRequestPresenter(PullRequestContract.View pullRequestActivity) {
        this.pullRequestActivity = pullRequestActivity;
    }

    public PullRequestPresenter() {
    }

    public void pullRequestRequisition (String full_name, final Context context){
        apiDao.pullRequestRequisition(full_name, context);
    }

    public void fillScreenPresenter (PullRequestList pullRequests){
        pullRequestActivity.fillScreen(pullRequests);
    }

    public void interruptProgressBar(){
        pullRequestActivity.interruptProgressBar();
    }
}
