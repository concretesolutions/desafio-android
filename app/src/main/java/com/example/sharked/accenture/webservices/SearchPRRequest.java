package com.example.sharked.accenture.webservices;

import com.example.sharked.accenture.APIConf;
import com.example.sharked.accenture.models.InfoContainer;
import com.example.sharked.accenture.models.PullRequest;
import com.example.sharked.accenture.models.Repository;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchPRRequest extends BaseRequestHandler<PullRequest[]> {

    private Repository repo;

    public SearchPRRequest(Repository repo){
        this.repo = repo;
    }

    @Override
    protected String getURL() {
        //https://api.github.com/repos/scwang90/SmartRefreshLayout/pulls
        return APIConf.SERVER_URL + String.format("repos/%s/pulls",repo.getFullName()) ;

    }

    @Override
    protected Class getResponseClass() {
        return PullRequest[].class;
    }

    @Override
    protected String getHttpMethod() {
        return "GET";
    }

}
