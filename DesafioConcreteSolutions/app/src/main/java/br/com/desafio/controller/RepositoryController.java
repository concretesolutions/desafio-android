package br.com.desafio.controller;

import android.support.annotation.UiThread;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;
import org.greenrobot.eventbus.EventBus;
import org.springframework.core.NestedRuntimeException;

import br.com.desafio.domain.Repositories;
import br.com.desafio.webclient.WebClient;

@EBean
public class RepositoryController implements RestErrorHandler {
    @RestService
    WebClient webClient;

    @AfterInject
    void afterInjectRepositoryController(){
        webClient.setRestErrorHandler(this);
    }

    @Background
    public void findRepository(Integer page, String language){
        Repositories repositories = webClient.findRepositoies(page, language);

        onResult(repositories);
    }

    @UiThread
    public void onResult(Repositories repositories){
        if(repositories != null)
            EventBus.getDefault().post(repositories);
        else
            EventBus.getDefault().post("Erro ao fazer requisição");
    }

    @UiThread
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        e.printStackTrace();
    }
}
