package com.example.desafioconcrete.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desafioconcrete.Objects.DetailsPull;
import com.example.desafioconcrete.Objects.GithubApiResponse;
import com.example.desafioconcrete.R;
import com.example.desafioconcrete.WebConnections.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullDetails extends AppCompatActivity {

    private Retrofit retrofit;
    private String baseURL;
    private String name;
    private String owner;
    private ListView pullList;
    private List<DetailsPull> detailsPullList;
    private ImageView toolBarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        baseURL = "https://api.github.com/repos/";

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            owner = intent.getExtras().getString("owner");
            name = intent.getExtras().getString("name");
        }

        referenceLayout();
        configRetrofitClient();
        getPullDetails();
    }

    private void referenceLayout(){
        pullList = findViewById(R.id.pullList);
        toolBarBack = findViewById(R.id.toolBarBack);
        toolBarBack.setVisibility(View.VISIBLE);

        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PullDetails.this.finish();
            }
        });
    }

    private void loadPullData(){
        if (detailsPullList != null){
            AdapterPullList adapterPullList = new AdapterPullList(this, detailsPullList);
            pullList.setAdapter(adapterPullList);

            pullList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailsPullList.get(i).getHtml_url()));
                    startActivity(intent);
                }
            });
        }
    }

    private void configRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getPullDetails(){
        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        final Call<List<DetailsPull>> detailsPullCall = retrofitClient.getPullValues(owner, name);

        detailsPullCall.enqueue(new Callback<List<DetailsPull>>() {
            @Override
            public void onResponse(Call<List<DetailsPull>> call, Response<List<DetailsPull>> response) {
                detailsPullList = response.body();
                loadPullData();
            }

            @Override
            public void onFailure(Call<List<DetailsPull>> call, Throwable t) {
                new AlertDialog.Builder(PullDetails.this)
                        .setTitle(getResources().getString(R.string.connection_failed))
                        .setMessage(getResources().getString(R.string.internet_needed))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PullDetails.this.finish();
                            }
                        }).show();
            }
        });
    }
}
