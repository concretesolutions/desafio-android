package com.challenge.accenture.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.challenge.accenture.R;
import com.challenge.accenture.Utils.EndlessRecyclerViewScrollListener;
import com.challenge.accenture.adapters.MainAdapter;
import com.challenge.accenture.adapters.PullAdapter;
import com.challenge.accenture.interfaces.RestClient;
import com.challenge.accenture.objects.Pull;
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

import java.util.ArrayList;
import java.util.List;


/*
 * Created by Cezhar Arevalo on 10/21/18.
 */
public class PullActivity extends AppCompatActivity {

    List<Pull> data = new ArrayList();
    PullAdapter adapter;
    RestClient restClient;
    String owner, repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulls);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));


        RecyclerView recyclerView = findViewById(R.id.pulls_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PullAdapter(this,data,R.layout.pull_row, imageLoader, this);
        recyclerView.setAdapter(adapter);

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

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            owner = extras.getString("owner");
            repo = extras.getString("repo");
            loadPulls(owner,repo);
        }

    }

    void loadPulls(final String owner, final String repo){
        Call<List<Pull>> call = restClient.getPulls(owner,repo);

        call.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if(response.isSuccessful()) {
                    data.clear();
                    data.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {
                Log.e("le test error", t.toString());
            }
        });
    }


    public void goToBrowser(Pull lePull) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lePull.getHtmlUrl()));
        startActivity(browserIntent);
    }
}
