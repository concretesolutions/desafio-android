package com.alexandreobs.testeconcrete.model.data.remote;

import com.alexandreobs.testeconcrete.model.pojo.pull.Request;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitRequestsAPI {

    @GET("repos/{creator}/{repository}/pulls")
    Observable<List<Request>> getPullRequests(@Path("creator") String creatorString,
                                              @Path("repository") String repoString,
                                              @Query("page") int pagina
    );
}



