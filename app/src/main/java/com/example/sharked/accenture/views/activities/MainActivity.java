package com.example.sharked.accenture.views.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharked.accenture.BaseActivity;
import com.example.sharked.accenture.R;
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


    @ViewById(R.id.repository_recycler)
    RecyclerView repositoryRecycler;
    private LinearLayoutManager recyclerLayoutManager;
    private RecyclerView.Adapter<CustomViewHolder> mAdapter;
    private ArrayList<Repository> mItems;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @AfterViews
    void init(){
        Log.i("init","___init____");
        Toast.makeText(this,"___init____", Toast.LENGTH_SHORT).show();
        mItems = new ArrayList<>();
        recyclerLayoutManager = new LinearLayoutManager(this);
        repositoryRecycler.setLayoutManager(recyclerLayoutManager);
        mAdapter = new RecyclerView.Adapter<CustomViewHolder>() {
            @Override
            public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_repository, viewGroup, false);
                return new CustomViewHolder(view);
            }



            @Override
            public void onBindViewHolder(CustomViewHolder viewHolder, int i) {
                viewHolder.noticeSubject.setText(mItems.get(i).getFullName());
            }

            @Override
            public int getItemCount() {
                return mItems.size();
            }

        };
        repositoryRecycler.setAdapter(mAdapter);


    }
    private class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView noticeSubject;

        public CustomViewHolder(View itemView) {
            super(itemView);
            noticeSubject = (TextView) itemView.findViewById(R.id.repository_name_text);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new SearchRepositories(1).execute();

    }

    @Subscribe
    void handleResponse (InfoContainer response){
        Log.i("handleResponse",response.getTotalCount());

        mItems.addAll(Arrays.asList(response.getItems()));

        mAdapter.notifyDataSetChanged();


    }








}
