package com.challenge.accenture.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.challenge.accenture.R;
import com.challenge.accenture.Utils.EndlessRecyclerViewScrollListener;
import com.challenge.accenture.adapters.MainAdapter;
import com.challenge.accenture.interfaces.RestClient;
import com.challenge.accenture.objects.Item;
import com.challenge.accenture.objects.Repository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*
 * Created by Cezhar Arevalo on 10/21/18.
 */
public class Main extends AppCompatActivity{

    private MainAdapter adapter;
    private List<Item> data = new ArrayList<>();
    RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));


        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(this,data,R.layout.main_row, imageLoader, this);
        recyclerView.setAdapter(adapter);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPage(page+1);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        // Add logging into retrofit 2.0
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        restClient = retrofit.create(RestClient.class);
        loadPage(1);

    }

    void loadPage(final int page){
        Call<Repository> call = restClient.getRepo(page);

        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                if(response.isSuccessful()) {
                    //data.clear();
                    data.addAll(response.body().getItems());
                    adapter.notifyItemRangeInserted(page-1,page);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.e("le test error", t.toString());
            }
        });
    }

    public void openPulls(Item leRepo){
        Intent k = new Intent(Main.this, PullActivity.class);
        k.putExtra("owner",leRepo.getOwner().getLogin());
        k.putExtra("repo", leRepo.getName());
        startActivity(k);
    }
}