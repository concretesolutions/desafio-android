package com.example.feliperecabarren.desafioandroidconcrete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import adapter.RepositoryDetailAdapter;
import adapter.RepositoryListAdapter;
import asynctasks.RepositoryAsyncTask;
import constants.UrlConstans;
import dto.RepositoryDetail;
import dto.RepositoryList;

public class RepositoryDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RepositoryDetailAdapter repositoryDetailAdapter;
    ArrayList<RepositoryDetail> repositoryDetails;
    UrlConstans urlConstans;
    private String creator;
    private String repository;
    private TextView textViewOpened;
    private TextView textViewClosed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("repository"));
        setContentView(R.layout.activity_detalle_repositorio);

        repositoryDetails = new ArrayList<>();
        urlConstans = new UrlConstans();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRepositorioDetail);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = getIntent().getExtras();
        creator = bundle.getString("creator");
        repository = bundle.getString("repository");
        String opened = bundle.getString("opened");
        String closed = bundle.getString("closed");

        textViewOpened = (TextView) findViewById(R.id.textViewOpened);
        textViewOpened.setText(bundle.getString("opened") + " opened");
        textViewClosed = (TextView) findViewById(R.id.textViewClosed);
        textViewClosed.setText(bundle.getString("closed") + " closed");


        callRepositoryUrl();
    }

    public void callRepositoryUrl(){
        try{
            String jsonObject = new RepositoryAsyncTask().execute(urlConstans.getRepositoryDetailUrl(creator,repository)).get();
            loadListRepository(jsonObject);
        }
        catch (Exception e){
            String error = e.toString();
        }

    }
    public void loadListRepository(String jsonObject){
        try{

            JSONArray jsonArray = new JSONArray(jsonObject.toString());

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObjectItem = jsonArray.getJSONObject(i);
                JSONObject userObject = jsonObjectItem.getJSONObject("user");
                repositoryDetails.add(new RepositoryDetail(
                        jsonObjectItem.getString("title"),
                        jsonObjectItem.getString("body"),
                        userObject.getString("avatar_url"),
                        userObject.getString("login"),
                        userObject.getString("login"),
                        jsonObjectItem.getString("html_url")

                ));
            }
            repositoryDetailAdapter =  new RepositoryDetailAdapter(repositoryDetails,this);
            repositoryDetailAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(repositoryDetailAdapter);
        }catch (JSONException e){

        }

    }
}
