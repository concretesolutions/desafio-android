package com.example.rpanaquecavana.gitandroid;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detalle;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private ArrayList<Detalle> detalles;
    private ListDetalleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_body);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerdetalle);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent().hasExtra("autor") && getIntent().hasExtra("repo")){

            String autor = getIntent().getStringExtra("autor");
            final String repositorio = getIntent().getStringExtra("repo");


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PostService postService = retrofit.create(PostService.class);

            final Call<DetailBody> requestDetalle = postService.getDetalle(autor, repositorio);
            Log.e("Tag", "REQUEST DETALLE : ");
            Log.e("Tag", "REQUEST DETALLE : " +requestDetalle.request());

            requestDetalle.enqueue(new Callback<DetailBody>()
            {
                @Override
                public void onResponse(Call<DetailBody> call, Response<DetailBody> response) {
                    Log.e("tag" , "ENTRO AQUI: " +response.code());

                    DetailBody detailBody = response.body();

                        detalles = new ArrayList<>(detailBody.getDetalles());
                        adapter = new ListDetalleAdapter(detalles);
                        recyclerView.setAdapter(adapter);



                    Log.e("tag" , "ENTRO AQUI: " );
                }

                @Override
                public void onFailure(Call<DetailBody> call, Throwable t) {
                    Log.e("tag" , "Paso Error : " +t.getMessage());
                }
            });



        }
    }
}
