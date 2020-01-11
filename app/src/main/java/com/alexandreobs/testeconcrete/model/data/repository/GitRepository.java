package com.alexandreobs.testeconcrete.model.data.repository;

import com.alexandreobs.testeconcrete.model.pojo.repositories.GitResult;

import io.reactivex.Observable;

import static com.alexandreobs.testeconcrete.model.data.remote.GitRetrofit.getApiService;

public class GitRepository {

    public Observable<GitResult> getRepo(int pagina) {
        return getApiService().getALLRepo(pagina);
    }
}
