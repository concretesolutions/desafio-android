package com.br.apigithub.aac;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.br.apigithub.MyApplication;
import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Pull;
import com.br.apigithub.interfaces.IGithubServiceProvider;
import com.br.apigithub.interfaces.INotifyViewModelAboutService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rlima on 30/05/18.
 */

public class RepositoryViewModel extends ViewModel implements INotifyViewModelAboutService {
    @Inject
    IGithubServiceProvider serviceProvider;
    private MutableLiveData<GithubRepository> githubLiveData;
    private MutableLiveData<List<Pull>> pullsLiveData;
    private MutableLiveData<String> msgError;
    private String userName;
    private String repo;

    public void init() {
        MyApplication.getComponent().inject(this);
    }

    public void listRepos(Integer page) {
        serviceProvider.listReposJava("star", page, this);
    }

    public void updateListRepos(Integer page, Integer limit) {
        serviceProvider.getPulls(userName, repo, page, limit, true, this);
    }

    public void updatePulls(Integer page, Integer limit) {
        serviceProvider.getPulls(userName, repo, page, limit, true, this);
    }

    public MutableLiveData<GithubRepository> getGithubLiveData() {
        if (githubLiveData == null) {
            githubLiveData = new MutableLiveData<>();
        }
        return githubLiveData;
    }

    public MutableLiveData<List<Pull>> getPullsLiveData() {
        if (pullsLiveData == null) {
            pullsLiveData = new MutableLiveData<>();
        }
        return pullsLiveData;
    }

    public MutableLiveData<String> getMsgError() {
        if (msgError == null) {
            msgError = new MutableLiveData<>();
        }
        return msgError;
    }

    @Override
    public void returnListRepos(GithubRepository list) {
        githubLiveData.postValue(list);
    }

    @Override
    public void updateListRepos(List<GithubRepository> list) {
        // FIXME implementar
    }

    @Override
    public void updatePullRequests(List<Pull> pulls) {
        List<Pull> oldValue = getPullsLiveData().getValue();
        oldValue.addAll(pulls);
        getPullsLiveData().postValue(oldValue);
    }

    @Override
    public void returnInfoAboutPullRequest(List<Pull> pulls) {
        // FIXME implementar
    }

    @Override
    public void returnPullRequests(List<Pull> pulls) {
        getPullsLiveData().postValue(pulls);
    }

    @Override
    public void notifyOnError(Throwable throwable) {
        getMsgError().postValue(throwable.getMessage());
    }
}
