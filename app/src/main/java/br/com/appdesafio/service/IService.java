package br.com.appdesafio.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * class responsible for performing requests with the server.
 */
public interface IService {

    /**
     * Service responsible for searching the web service repository list.
     *
     * @param language, is the language of the repository.
     * @param sort,is the sorting of the list.
     * @return
     */
    @GET("/search/repositories")
    Call<JsonObject> getListRepository(@Query("q") String language,
                                       @Query("sort") String sort,
                                       @Query("page") String page);


    /**
     *Service responsible for fetching the pull request list from a web service repository.
     * @param creator, creator of the repository.
     * @param repository repository name.
     * @return
     */
    @GET("/repos/{criador}/{repositorio}/pulls")
    Call<JsonArray> getListPullRequest (@Path("criador") String creator,
                                        @Path("repositorio") String repository);



}
