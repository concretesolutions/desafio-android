package br.com.appdesafio.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IService {
    /**
     *service responsible for logging in to the web service.
     * @param contentType
     * @return
     */


    /**
     * service responsible for searching the list of dogs in the web service.
     *
     * @param
     * @param
     * @return
     */
    @GET("/search/repositories")
    //https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1
    Call<JsonObject> getListRepository(@Query("q") String language,
                                       @Query("sort") String sort,
                                       @Query("page") String page);
/*    Call<FilterResponse> getFilterList(@Path("Id") long customerId,
                                       @Query("Type") String responseType,
                                       @Query("SearchText") String searchText);*/

    //https://api.github.com/repos/<criador>/<repositório>/pulls
    @GET("/repos/{criador}/{repositorio}/pulls")
    Call<JsonArray> getListPullRequest (@Path("criador") String creator,
                                        @Path("repositorio") String repository);
//https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1


}
