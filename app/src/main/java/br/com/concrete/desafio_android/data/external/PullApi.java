package br.com.concrete.desafio_android.data.external;

import java.util.ArrayList;
import br.com.concrete.desafio_android.domain.Pull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PullApi {

    @GET("repos/{creator}/{repository}/pulls")
    Call<ArrayList<Pull>> getPulls(@Path("creator") String creator,
                                   @Path("repository") String repository);

}
