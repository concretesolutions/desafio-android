package com.alexandreobs.testeconcrete.model.data.remote;

import com.alexandreobs.testeconcrete.model.pojo.repositories.GitResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitAPI {

    @GET("search/repositories?q=language:Java&sort=stars")

    Observable<GitResult> getALLRepo(

            @Query("page") int pagina
    );
}
