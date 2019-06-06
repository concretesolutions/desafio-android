package br.com.desafio.controller;

import android.support.annotation.UiThread;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;
import org.greenrobot.eventbus.EventBus;
import org.springframework.core.NestedRuntimeException;

import java.util.List;

import br.com.desafio.domain.PullRequest;
import br.com.desafio.webclient.WebClient;

@EBean
public class PullRequestController implements RestErrorHandler {
    @RestService
    WebClient webClient;

    @AfterInject
    void afterInjectRepositoryController(){
        webClient.setRestErrorHandler(this);
    }

    @Background
    public void findPullRequesties(String owner, String repository){
        onResult(webClient.findPullRequests(owner, repository));
    }

    @UiThread
    public void onResult(List<PullRequest> pullRequests){
        if(pullRequests != null)
            EventBus.getDefault().post(pullRequests);
        else
            EventBus.getDefault().post("Erro ao fazer requisição");
    }

    @UiThread
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        e.printStackTrace();
    }
}
