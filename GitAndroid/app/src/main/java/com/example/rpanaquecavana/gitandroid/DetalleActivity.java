package com.example.rpanaquecavana.gitandroid;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private ArrayList<Detail> data = new ArrayList<>();
    private ListDetalleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_body);


        if(getIntent().hasExtra("autor") && getIntent().hasExtra("repo")){

            String autor = getIntent().getStringExtra("autor");
            final String repositorio = getIntent().getStringExtra("repo");


            recyclerView = (RecyclerView) findViewById(R.id.recyclerdetalle);
            recyclerView.setHasFixedSize(true);


            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PostService postService = retrofit.create(PostService.class);

            final Call<ArrayList<Detail>> requestDetalle = postService.getDetalle(autor, repositorio);

            requestDetalle.enqueue(new Callback<ArrayList<Detail>>()
            {
                @Override
                public void onResponse(Call<ArrayList<Detail>> call, Response<ArrayList<Detail>> response) {
                    Log.e("tag" , "ENTRO AQUI: " +response.code());

                /*    List<Detail>  body = response.body();
                    data = new ArrayList<>(body);
                    adapter = new ListDetalleAdapter(data);
                    recyclerView.setAdapter(adapter);
*/
                    ArrayList<Detail> body = response.body();
                    data = new ArrayList<>(body);
                    adapter = new ListDetalleAdapter(data);
                    recyclerView.setAdapter(adapter);

                    Log.e("HASIL", "onResponse: "+data);
                }

                @Override
                public void onFailure(Call<ArrayList<Detail>> call, Throwable t) {
                    Log.e("tag" , "Paso Error : " +t.getMessage());
                }
            });



        }
    }
}
