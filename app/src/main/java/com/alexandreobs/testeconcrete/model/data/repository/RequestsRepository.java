package com.alexandreobs.testeconcrete.model.data.repository;

import com.alexandreobs.testeconcrete.model.pojo.pull.Request;

import java.util.List;

import io.reactivex.Observable;

import static com.alexandreobs.testeconcrete.model.data.remote.RequestsRetrofit.getApiService;

public class RequestsRepository {

    public Observable<List<Request>> getRequest(String creatorString, String repoString, int pagina){
        return getApiService().getPullRequests(creatorString, repoString, pagina);
    }
}
