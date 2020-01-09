package com.alexandreobs.testeconcrete.model.data.repository;

import com.alexandreobs.testeconcrete.model.pojo.GitResult;

import io.reactivex.Observable;

import static com.alexandreobs.testeconcrete.model.data.remote.RetrofitService.getApiService;

public class GitRepository {

    public Observable<GitResult> getRepo()

    {

        return getApiService().getALLRepo();
    }
}
