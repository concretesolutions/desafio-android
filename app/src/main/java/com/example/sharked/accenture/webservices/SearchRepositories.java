package com.example.sharked.accenture.webservices;

import com.example.sharked.accenture.APIConf;
import com.example.sharked.accenture.models.InfoContainer;

public class SearchRepositories extends BaseRequestHandler {

    private int page;

    public SearchRepositories(int page){
        this.page = page;
    }

    @Override
    protected String getURL() {
        return APIConf.SERVER_URL + "search/repositories?q=language:Java&sort=stars&page=" + String.valueOf(page);
    }

    @Override
    protected Class getResponseClass() {
        return InfoContainer.class;
    }

    @Override
    protected String getHttpMethod() {
        return null;
    }

}
