package br.com.concrete.desafio_android.data.external;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Pull;
import br.com.concrete.desafio_android.presenter.PullPresenter;
import br.com.concrete.desafio_android.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullRequest {

    private static final PullRequest INSTANCE = new PullRequest();
    private PullRequest(){ }
    public static PullRequest getInstance(){
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build();

    PullApi api = retrofit.create(PullApi.class);

    public void loadPullRequests(String creator, final String repository){
        Call<ArrayList<Pull>> queue = api.getPulls(creator, repository);
        Log.e("URL:", api.getPulls(creator, repository).request().url().toString());
        queue.enqueue(new Callback<ArrayList<Pull>>() {
            @Override
            public void onResponse(Call<ArrayList<Pull>> call, Response<ArrayList<Pull>> response) {
                if(response.code() == 200){
                    PullPresenter.getInstance().updateUIList(response.body());
                }else{
                    Log.e("ERROR:", Integer.toString(response.code()));
                    PullPresenter.getInstance().logMessageToUser(R.string.issue_not_known);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pull>> call, Throwable t) {
                t.printStackTrace();
                PullPresenter.getInstance().logMessageToUser(R.string.connection_problem);
            }
        });
    }

}
