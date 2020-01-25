package br.com.victoramaral.githubdive.model.data;

import br.com.victoramaral.githubdive.model.pojos.Repositories;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestsApi {

    @GET("repos/<full name>/pulls")
    Observable<Repositories> getAllRequests(@Path("full name") String fullName,
                                            @Query("page") int page);
}
