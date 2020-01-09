package com.alexandreobs.testeconcrete.model.data.remote;

import com.alexandreobs.testeconcrete.model.pojo.GitResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GitAPI {

    @GET("search/repositories?q=language:Java&sort=stars")

    Observable<GitResult> getALLRepo(

    );
}
