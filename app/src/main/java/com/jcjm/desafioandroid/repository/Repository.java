package com.jcjm.desafioandroid.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.jcjm.desafioandroid.network.ApiCallInterface;
import com.jcjm.desafioandroid.util.Constant;

import io.reactivex.Observable;



public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call news api
     * */
    public Observable<JsonElement> executeGitRepositoriesApi(int page) {
        return apiCallInterface.getRepositories(Constant.Q_PARAMETER, Constant.SORT_PARAMETER, String.valueOf(page));
    }

    public Observable<JsonArray> executePullRequestApi(String login, String repository) {
        return apiCallInterface.getPulls(login,repository);
    }


}
