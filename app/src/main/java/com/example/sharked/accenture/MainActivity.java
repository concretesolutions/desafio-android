package com.example.sharked.accenture;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharked.accenture.adapters.RepositoryAdapter;
import com.example.sharked.accenture.models.InfoContainer;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.webservices.SearchRepositories;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    RepositoryAdapter adapter;

    @ViewById(R.id.repository_list_view)
    ListView repositoryListView;


    @AfterViews
    void init(){
        Log.i("init","___init____");
        Toast.makeText(this,"___init____", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        new SearchRepositories(1).execute();


    }

    @Subscribe
    void handleResponse (InfoContainer response){
        Log.i("handleResponse",response.getTotalCount());
        for (Repository item : response.getItems()){Log.i("item",item.getFullName());}
        if (null == adapter){
            RepositoryAdapter adapter = new RepositoryAdapter(this,response.getItems());
            repositoryListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }





}
