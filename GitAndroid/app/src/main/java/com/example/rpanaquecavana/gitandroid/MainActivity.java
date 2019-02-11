package com.example.rpanaquecavana.gitandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.rpanaquecavana.gitandroid.Modelos.Item;
import com.example.rpanaquecavana.gitandroid.Modelos.RepoGit;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
   // private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<Item> data;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

     //   textView = (TextView) findViewById(R.id.textlist);
        recyclerView = (RecyclerView) findViewById(R.id.recyclervista);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        //instaciamos el Builder de Retrofit el cuál sera el encargado de configurar nuestra petición
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService postService = retrofit.create(PostService.class);


        Call<RepoGit> requestRepo = postService.getRepositorio("language" + ":" + "Java", "stars", 1);

        // enqueue  recibe un objeto tipo Callback como parámetro
        requestRepo.enqueue(new Callback<RepoGit>() {
            @Override
            public void onResponse(Call<RepoGit> call, Response<RepoGit> response) {
                if (!response.isSuccessful()) {
                 //   textView.setText("code: " + response.code());
                    return;
                }

                RepoGit jsonResponse = response.body();
                data = new ArrayList<>(jsonResponse.getItems());
                adapter = new ListAdapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<RepoGit> call, Throwable t) {
               // textView.setText(t.getMessage());
            }
        });


    }
}
