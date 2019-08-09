package sergio.com.br.desafioandroid.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sergio.com.br.desafioandroid.app.Constants.END_POINTS.PULL_REQUEST_URL
import sergio.com.br.desafioandroid.app.Constants.END_POINTS.SEARCH_URL
import sergio.com.br.desafioandroid.models.PullRequestModel
import sergio.com.br.desafioandroid.models.SearchListModel

interface APIInterface {
    //region GET
    @GET(SEARCH_URL)
    fun loadCharacters(@Query(value = "page", encoded = true) id: Int): Call<SearchListModel>

    @GET(PULL_REQUEST_URL)
    fun loadPullRequests(
        @Path(value = "creator", encoded = true) creator: String, @Path(
            value = "repository",
            encoded = true
        ) repository: String
    ): Call<ArrayList<PullRequestModel>>

    //endregion

    //region POST
    //endregion

    //region DELETE
    //endregion
}