package br.com.concrete.desafio_android.data.external;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Repository;
import br.com.concrete.desafio_android.domain.RepositoryResponse;
import br.com.concrete.desafio_android.presenter.RepositoryPresenter;
import br.com.concrete.desafio_android.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daivid
 * Dedicated class to Manipulate Requests
 * Here we use a singleton to not be necessary recreate
 *      the same object more than one time
 */
public class RepositoryRequest {

    private static final RepositoryRequest INSTANCE = new RepositoryRequest();
    private RepositoryRequest(){ }
    public static RepositoryRequest getInstance(){
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build();

    RepositoryApi api = retrofit.create(RepositoryApi.class);

    /**
     * THIS method do a request to github server to retrieve repositories based on the followed parameters
     * @param language
     * @param pageNumber
     */
    public void loadRepositories(String language, int pageNumber){
        Call<RepositoryResponse> queue = api.getRepositories(language, Constants.TYPE_SORT, pageNumber);
        Log.d("SOL:", api.getRepositories(language, Constants.TYPE_SORT, pageNumber).toString());
        queue.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                if(response.code() == 200){
                    RepositoryPresenter.getInstance().updateUIList((ArrayList<Repository>) response.body().getItems());
                }else if(response.code() == 403){
                    RepositoryPresenter.getInstance().logMessageToUser(R.string.git_hub);
                }else{
                    RepositoryPresenter.getInstance().logMessageToUser(R.string.issue_not_known);
                }
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                t.printStackTrace();
                RepositoryPresenter.getInstance().logMessageToUser(R.string.connection_problem);
            }
        });
    }

}
