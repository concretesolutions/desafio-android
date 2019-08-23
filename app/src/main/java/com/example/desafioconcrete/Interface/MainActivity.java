package com.example.desafioconcrete.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desafioconcrete.Objects.DetailsRepository;
import com.example.desafioconcrete.Objects.GithubApiResponse;
import com.example.desafioconcrete.R;
import com.example.desafioconcrete.WebConnections.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String baseURL;
    private ListView repositoryList;
    private GithubApiResponse githubApiResponse;
    private boolean flagLoading;
    private int page;
    private AdapterRepositoryList adapterRepositoryList;
    private int backCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        baseURL = "https://api.github.com/search/";
        flagLoading = false;
        page = 1;
        backCount = 0;

        layoutReference();
        configRetrofitClient();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String repositoryData = intent.getExtras().getString("repositoryData");
            Gson gson = new Gson();
            githubApiResponse = gson.fromJson(repositoryData, new TypeToken<GithubApiResponse>(){}.getType());
            populateRepositoryList();
        }
    }

    private void layoutReference(){
        repositoryList = findViewById(R.id.repositoryList);
        ImageView toolBarBack = findViewById(R.id.toolBarBack);
        toolBarBack.setVisibility(View.GONE);
    }

    private void populateRepositoryList(){
        if (githubApiResponse != null) {
            adapterRepositoryList = new AdapterRepositoryList(this, githubApiResponse);
            repositoryList.setAdapter(adapterRepositoryList);

            repositoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, PullDetails.class);
                    intent.putExtra("owner", githubApiResponse.getDetailsRepositoryList().get(i).getOwner().getLogin());
                    intent.putExtra("name", githubApiResponse.getDetailsRepositoryList().get(i).getName());
                    startActivity(intent);
                }
            });

            repositoryList.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0){
                        if (!flagLoading) {
                            flagLoading = true;
                            page = page + 1;
                            getMoreJavaRepositories();
                        }
                    }
                }
            });
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.connection_failed))
                    .setMessage(getResources().getString(R.string.internet_needed))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
        }
    }

    private void configRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getMoreJavaRepositories(){
        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<GithubApiResponse> githubApiResponseCall = retrofitClient.getRepositoryValues("language:Java", "stars", String.valueOf(page));

        githubApiResponseCall.enqueue(new Callback<GithubApiResponse>() {
            @Override
            public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                GithubApiResponse newRepositoryData = response.body();
                githubApiResponse.addDetailsRepositoryList(newRepositoryData.getDetailsRepositoryList());

                flagLoading = false;
                adapterRepositoryList.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                 new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getResources().getString(R.string.connection_failed))
                        .setMessage(getResources().getString(R.string.internet_needed))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                page = page - 1;
                            }
                        }).show();
            }
        });
    }

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            flagLoading = false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onBackPressed() {
        if (backCount == 1){
            MainActivity.this.finish();
            System.exit(0);
        } else {
            Toast.makeText(this, getResources().getString(R.string.pressToExit), Toast.LENGTH_LONG).show();
            backCount++;
        }
    }
}
